package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;


import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Requests.RequestEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Services.Notifications.NotificationsService;
import com.devx.software.ferias.Services.Requests.RequestsService;
import com.devx.software.ferias.Repositories.Requests.RequestsRepository;
import com.devx.software.ferias.Mappers.Requests.RequestListMapper;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Repositories.Events.EventsRepository;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@PreAuthorize("isAuthenticated()")
public class RequestsController {
    private final HttpHeaders headers = new HttpHeaders();
    private final RequestsRepository requestsRepository;

    private final UserService userService;

    private final RequestListMapper requestListMapper;
    private final RequestsService requestsService;

    private final CatalogsService catalogsService;
    private final EnterprisesService directorioEmpresarialService;


    private final EventsRepository eventsRepository;

    private final SistemaRepository sistemaRepository;

    private final NotificationsService notificationsService;

    @Autowired
    public RequestsController(
            RequestsRepository requestsRepository,
            UserService userService,
            RequestListMapper requestListMapper, RequestsService requestsService, CatalogsService catalogsService,
            EnterprisesService directorioEmpresarialService,
            EventsRepository eventsRepository,
            SistemaRepository sistemaRepository, NotificationsService notificationsService) {
        this.requestsRepository = requestsRepository;
        this.userService = userService;
        this.requestListMapper = requestListMapper;
        this.requestsService = requestsService;
        this.catalogsService = catalogsService;
        this.directorioEmpresarialService = directorioEmpresarialService;
        this.eventsRepository = eventsRepository;
        this.sistemaRepository = sistemaRepository;
        this.notificationsService = notificationsService;
    }

    @GetMapping("/formRequiredData/{idSolicitud}")
    public ResponseEntity<HashMap<String, Object>> formRequiredData(@PathVariable Long idSolicitud) {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();

            List<CatalogsDTO> areas = this.catalogsService.getAllCatalogosByTipoCatalogo("AREAS");
            response.put("areas", areas);

            //List<EnterpriseEntity> empresas = this.directorioEmpresarialService.pageByEstatus("VALIDADO");
            //response.put("empresas", empresas);

            //List<UserEntity> responsables = this.userService.pageByTipoUsuario("PLATAFORMA");
            //response.put("responsables", responsables);

            //List<EventEntity> eventos = this.eventsRepository.findAll();
            //response.put("eventos", eventos);

            if (idSolicitud == 0) {
                response.put("solicitud", null);
            } else {
                RequestEntity solicitud = this.requestsRepository.findById(idSolicitud).get();
                if (solicitud != null) {
                    if (solicitud.getFolio() == null) {
                        solicitud.setFolio(this.folioSolicitud(solicitud.getId(), solicitud.getCreatedAt()));
                        solicitud = this.requestsRepository.save(solicitud);
                    }
                    response.put("solicitud", solicitud);
                } else {
                    throw new Exception("No hay información");
                }
            }

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<RequestEntity> createSolicitud(@RequestBody RequestEntity solicitud) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );

            solicitud.setUsuarioSolicitanteId( usuario );

            solicitud = requestsRepository.save(solicitud);
            solicitud.setFolio(this.folioSolicitud(solicitud.getId(), solicitud.getCreatedAt()));
            solicitud = requestsRepository.save(solicitud);

            Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail(" Solicitud de servicio recibida", solicitud.getUsuarioSolicitanteId().getEmail() , "Su solicitud de servicio ha sido recibida con folio: " +  solicitud.getFolio() +", puede dar seguimiento al mismo en su panel de solicitudes." );

            NotificationsEntity ne = new NotificationsEntity();
            ne.setTexto( "Se ha recibido una solicitud de servicio con folio: " + solicitud.getFolio() + ".");
            ne.setVista(Boolean.FALSE);
            ne.setCreatedAt(new Date());
            ne.setTipo(Long.valueOf(1));
            ne.setDestinatario(solicitud.getUsuarioEncargadoId());
            this.notificationsService.create(ne);

            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(solicitud, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{perfil}")
    public ResponseEntity<PaginationResponse>  page(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String perfil
    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );
            Pageable pegeable = PageRequest.of(page, size);
            Page<RequestEntity> pageDataset = this.requestsService.pageRequests(
                    pegeable,
                    usuario.getId(),
                    perfil
            );

            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.requestListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<RequestEntity> update(@RequestBody RequestEntity solicitud) {
        HttpHeaders headers = new HttpHeaders();
        try {
            solicitud.setEstatus( !solicitud.getEstatus() );
            solicitud = this.requestsRepository.save(solicitud);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(solicitud, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String folioSolicitud(Long id, Date createdAt) {
        String idSolicitud = Long.toString(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(createdAt);
        return "S/" + fecha + "/" + idSolicitud;
    }

    @GetMapping("/reportesolicitudes")
    public ResponseEntity<HashMap<String, Object>> reporteSolicitudes() {
        HttpHeaders headers = new HttpHeaders();
        try {

            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            formResources.put("sexo", this.requestsService.obtenerTotalPorSexo());
            formResources.put("municipio", this.requestsService.obtenerTotalPorMunicipio());
            formResources.put("sector", this.requestsService.obtenerTotalPorsector());
            //formResources.put("subsectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SUBSECTORES"));
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
