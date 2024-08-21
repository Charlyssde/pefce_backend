package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListUserFilterDTO;
import com.devx.software.ferias.Entities.Autodiagnosticos.AutodiagnosticoEntity;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Events.EventUsersEntity;
import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Services.Encuestas.EncuestasService;
import com.devx.software.ferias.Services.Events.EventsService;
import com.devx.software.ferias.DTOs.Events.EventContactPageDTO;
import com.devx.software.ferias.Entities.Encuestas.EmpresasEncuestasEntity;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Repositories.Events.EventsRepository;

import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Services.Notifications.NotificationsService;


import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Repositories.Encuestas.EmpresasEncuestasEntityRepository;
import com.devx.software.ferias.Repositories.Encuestas.EncuestaRepository;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventsController {

    private final EventsRepository eventsRepository;
    private final CatalogsService catalogsService;
    private final EnterprisesService directorioEmpresarialService;
    private final UserService userService;
    private final EventsService eventsService;
    private final EncuestasService encuestasService;
    private final SistemaRepository sistemaRepository;
    private final EncuestaRepository encuestaRepository;
    private final NotificationsService notificationsService;
    private final EmpresasEncuestasEntityRepository empresasEncuestasEntityRepository;
    private final EnterprisesService enterprisesService;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
       public EventsController(EventsRepository eventsRepository, CatalogsService catalogsService, EnterprisesService directorioEmpresarialService, UserService userService, EventsService eventsService, EncuestasService encuestasService, SistemaRepository sistemaRepository, EncuestaRepository encuestaRepository, NotificationsService notificationsService, EmpresasEncuestasEntityRepository empresasEncuestasEntityRepository, EnterprisesService enterprisesService) {
        this.eventsRepository = eventsRepository;
        this.catalogsService = catalogsService;
        this.directorioEmpresarialService = directorioEmpresarialService;
        this.userService = userService;
        this.eventsService = eventsService;
        this.encuestasService = encuestasService;
        this.sistemaRepository = sistemaRepository;
        this.encuestaRepository = encuestaRepository;
        this.notificationsService = notificationsService;
        this.empresasEncuestasEntityRepository = empresasEncuestasEntityRepository;
        this.enterprisesService = enterprisesService;
    }

    @GetMapping("")
    public ResponseEntity<List<EventEntity>> getEventos() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<EventEntity> eventos = this.eventsRepository.findAll();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(eventos, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/formRequiredData/{idEvento}")
    public ResponseEntity<HashMap<String, Object>> formRequiredData(@PathVariable Long idEvento) {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();

            List<CatalogsDTO> areas = this.catalogsService.getAllCatalogosByTipoCatalogo("AREAS");
            response.put("areas", areas);

            List<EnterpriseEntity> empresas = this.directorioEmpresarialService.pageByEstatus("VALIDADO");
            response.put("empresas", empresas);

            if (idEvento == 0) {
                response.put("evento", null);
            } else {
                EventEntity evento = eventsRepository.findEventoById(idEvento);
                if (evento != null) {
                    if (evento.getFolio() == null) {
                        evento.setFolio(this.folioEvento(evento.getId(), evento.getCreatedAt()));
                        evento = this.eventsRepository.save(evento);
                    }
                    response.put("evento", evento);
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

    private String folioEvento(Long id, Date createdAt) {
        String idEvento = Long.toString(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(createdAt);
        return "P/" + fecha + "/" + idEvento;
    }

    @GetMapping("/page")
    public ResponseEntity<List<EventEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<EventEntity> response = this.eventsRepository.findAllByStatus();

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response,headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EventEntity> create(
            @RequestParam(name = "evento") String evento,
            @RequestParam(name = "datosimagen") String datosimagen,
            @RequestParam(name = "archivoimagen") MultipartFile archivoimagen
            ) { //@RequestBody EventEntity entity
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );

            ObjectMapper objectMapper = new ObjectMapper();
            EventEntity eventoTmp = objectMapper.readValue(evento, EventEntity.class) ;
            FileEntity datosimagenTmp = objectMapper.readValue(datosimagen, FileEntity.class);

            eventoTmp.setResponsableId( usuario );
            eventoTmp.setArchivoimagen( null );
            eventoTmp.setArchivo( null );

            EventEntity responseData = eventsService.create( eventoTmp, datosimagenTmp, archivoimagen );
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createEventoDirectorio")
    public ResponseEntity<EventUsersEntity> createEventoDirectorio(@RequestBody EventUsersEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            EmpresasEncuestasEntity saveEntity = new EmpresasEncuestasEntity();
               String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );
            List<Encuestas> EncuestaByEvento = encuestaRepository.findByEvento(entity.getEvento().getId());
            EnterpriseListUserFilterDTO enterpriseEntity = this.enterprisesService.findByUserId(usuario.getId());
            EnterpriseEntity enterprise = this.enterprisesService.findById(enterpriseEntity.getId());
            
            for (Encuestas encuesta : EncuestaByEvento) {
                saveEntity.setEncuesta(encuesta);
                saveEntity.setEmpresa(enterprise);
                empresasEncuestasEntityRepository.save(saveEntity);
            }
            
            empresasEncuestasEntityRepository.save(saveEntity);
            EventUsersEntity responseData = eventsService.agregarParticipante(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelarevento")
    public ResponseEntity<EventUsersEntity> cancelarEvento(@RequestBody EventUsersEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Void responseData = eventsService.cancelarParticipacion(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(entity, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageContactosByIdEvento/{id}")
    public ResponseEntity<List<EventContactPageDTO>> pageContactosByIdEvento(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<EventContactPageDTO> responseData = null;
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reportesolicitudes")
    public ResponseEntity<HashMap<String, Object>> reporteSolicitudes() {
        HttpHeaders headers = new HttpHeaders();
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            formResources.put("sexo", this.eventsService.obtenerTotalPorSexo());
            formResources.put("municipio", this.eventsService.obtenerTotalPorMunicipio());
            formResources.put("sector", this.eventsService.obtenerTotalPorsector());
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enviarencuestas")
    public ResponseEntity<HashMap<String, Object>> enviarencuestas(@RequestBody EventEntity entity) {

        HttpHeaders headers = new HttpHeaders();
        try {

            List<EncuestaEntity> encuestas = this.encuestasService.getAll();

            List<UserEntity> participantes = entity.getUsuarios();
            participantes.forEach(( p )-> {
                Mailgun mailgun = new Mailgun();
                try {
                    mailgun.sendBasicEmail(entity.getNombreEvento() + " - Encuesta de satisfacción", p.getEmail() , " por favor ingrese a nuestra platforma y llene la encuesta de satisfacción que puede descargar en el apartado del evento: " + entity.getNombreEvento() + " , solicitamos la descargue, la llene y la firme, para hacerla llegar al correo: " + entity.getResponsableId().getEmail() );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });

            try {

                List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
                UserEntity responsable = sistemaEntity.get(0).getResponableDce();

                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto( "Se ha enviado por correo electrónico la encuesta de satisfacción correspondiente del evento: " + entity.getNombreEvento() + ", a los participantes registrados.");
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                ne.setTipo(Long.valueOf(1));
                ne.setDestinatario(responsable);
                this.notificationsService.create(ne);

            } catch (Exception e) {
                return null;
            }

            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();

            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enviarcorreo")
    public ResponseEntity<EventEntity> enviarcorreo(
            @RequestParam(name = "evento") String evento,
            @RequestParam(name = "correo", required = true) String correo

    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ObjectMapper objectMapper = new ObjectMapper();
            EventEntity eventoTmp = objectMapper.readValue(evento, EventEntity.class) ;

            Mailgun mailgun = new Mailgun();
            try {
                mailgun.sendBasicEmail(eventoTmp.getNombreEvento() + " - Encuesta de satisfacción", email , " por favor ingrese a nuestra platforma y llene la encuesta de satisfacción que puede descargar en el apartado del evento: " + eventoTmp.getNombreEvento() + " , solicitamos la descargue, la llene y la firme, para hacerla llegar al correo: " + eventoTmp.getResponsableId().getEmail() );

                headers.set("200", "Correo enviado!!!");
                HashMap<String, Object> formResources = new HashMap<>();
                return new ResponseEntity(formResources, HttpStatus.OK);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<EventEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            EventEntity responseData = eventsService.findById(id);
            if(responseData!=null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<EventEntity> update(
            @RequestParam(name = "evento") String evento,
            @RequestParam(name = "datosimagen", required = false) String datosimagen,
            @RequestParam(name = "archivoimagen", required = false) MultipartFile archivoimagen
    ) { //@RequestBody EventEntity entity
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );
            ObjectMapper objectMapper = new ObjectMapper();
            EventEntity eventoTmp = objectMapper.readValue(evento, EventEntity.class) ;
            FileEntity datosimagenTmp = objectMapper.readValue(datosimagen, FileEntity.class);
            eventoTmp.setResponsableId( usuario );
            eventoTmp.setArchivoimagen( null );
            eventoTmp.setArchivo( null );
            EventEntity responseData = eventsService.update( eventoTmp, datosimagenTmp, archivoimagen );
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/pageUsuariosByIdEvento/{id}")
    public ResponseEntity<EventEntity> pageUsuariosByIdCapacitacion(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            EventEntity responseData = eventsService.findById(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByIdUsuario/{id}")
        public ResponseEntity<List<EventEntity>> findByIdUsuario(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
           List<EventEntity> responseData = eventsService.listByIdUsuario(id);
           if(responseData!=null) {
               headers.set("200", "Consulta exitosa");
               return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
           } else {
               return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
           }
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/page/}")
    public ResponseEntity<PaginationResponse>  page(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );
            Pageable pegeable = PageRequest.of(page, size);
            Page<RequestEntity> pageDataset = this.eventsService.pageRequests(
                    pegeable,
                    usuario.getId()
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
    */

//
//    private MinutasEntity createMinuta(EventosEntity entity){
//        MinutasEntity minuta = new MinutasEntity();
//
//        try{
//            minuta.setFolio("Folio");
//            minuta.setAsunto(entity.getNombreEvento());
//            minuta.setObjetivo(entity.getDescripcion());
//            if("virtual".equals(entity.getModalidad())){
//                minuta.setSede("Virtual");
//
//            }
//            else{
//                minuta.setSede(entity.getSede());
//            }
//            minuta.setPuntosTratados("Inicial");
//
//            CatalogsEntity tipoMinuta = this.catalogosService.getCatalogoByTipoAndNombre("TIPO_MINUTA", "Evento");
//            minuta.setTipoMinuta(tipoMinuta);
//
//            CatalogsEntity claseMinuta = this.catalogosService.getCatalogoByTipoAndNombre("CLASE_MINUTA", "Evento");
//            minuta.setClaseMinuta(claseMinuta);
//
//            minuta.setIdClase(entity.getId());
//            minuta.setFecha(new Date());
//            minuta.setCreatedAt(new Date());
//
//            MinutasEntity resp = this.minutasService.create(minuta);
//
//            return resp;
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    private ProyectosEntity createProyecto(EventosEntity entity){
//        ProyectosDTO proyecto = new ProyectosDTO();
//        try{
//            proyecto.setEmpresaId(directorioEmpresarialMapper.entityToDTO(entity.getEmpresaId()));
//            proyecto.setUsuarioEmpresaId(usuariosMapper.entityToDTO(entity.getUsuarioEmpresaId()));
//            proyecto.setResponsableId(usuariosMapper.entityToDTO(entity.getResponsableId()));
//            proyecto.setNombreProyecto(entity.getNombreEvento());
//            proyecto.setDescripcion(entity.getDescripcion());
//            proyecto.setFechaInicio(entity.getFechaInicio());
//            proyecto.setFechaFin(entity.getFechaFin());
//            proyecto.setArea(catalogosMapper.entityToDTO(entity.getAreaId()));
//            proyecto.setActivo(true);
//
//            CatalogsEntity estatus = catalogosService.findById(entity.getEstatus());
//            proyecto.setEstatus(catalogosMapper.entityToDTO(estatus));
//            proyecto.setCreatedAt(new Date());
//
//            ProyectosEntity resp = proyectosService.create(proyecto);
//            return resp;
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//
//
//    }
//

//
//

//

//
//    @DeleteMapping("/deleteById/{id}")
//    public ResponseEntity<EventosEntity> delete(@PathVariable Long id) {
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            EventosEntity responseData = eventosService.findById(id);
//            if(responseData!=null) {
//                eventosService.deleteById(id);
//                headers.set("200", "Borrado exitoso");
//                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
//            } else {
//                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/empresasByEstatus/{estatus}")
//    public ResponseEntity<List<DirectorioEmpresarialEntity>> pageByEstatus(@PathVariable String estatus) {
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            List<DirectorioEmpresarialEntity> responseData = directorioEmpresarialService.pageByEstatus(estatus);
//            headers.set("200", "Consulta exitosa");
//            return new ResponseEntity<>(responseData,headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//

//
//    @GetMapping("/pageDirectoriosByIdEvento/{id}")
//    public ResponseEntity<List<EventoEmpresaEntity>> page(@PathVariable Long id) {
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            List<EventoEmpresaEntity> getPageList = eventoEmpresaService.listByIdEvento(id);
//            headers.set("200", "Consulta exitosa");
//            return new ResponseEntity<>(getPageList,headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/estatusEventoDirectorio/{id}")
//    public ResponseEntity<EventoEmpresaEntity> updateEventoEmpresa(@PathVariable Long id, @RequestBody EventoEmpresaEntity model){
//        HttpHeaders headers = new HttpHeaders();
//        try{
//            EventoEmpresaEntity data = eventoEmpresaService.findById(id);
//            if(data != null){
//                EventoEmpresaEntity resp = eventoEmpresaService.update(model);
//                headers.set("200", "Actualización exitosa");
//                return new ResponseEntity<>(resp,headers,HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity("No existe el registro que solicita", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/deleteEmpresaDirectorio/{id}")
//    public ResponseEntity<EventoEmpresaEntity> deleteEventoEmpresa(@PathVariable Long id){
//        HttpHeaders headers = new HttpHeaders();
//        try{
//            EventoEmpresaEntity data = eventoEmpresaService.findById(id);
//            if(data != null){
//                eventoEmpresaService.delete(data);
//                headers.set("200", "Borrado exitoso");
//                return new ResponseEntity<>(data,headers,HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity("No existe el registro que solicita", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/updateFile/{idEvento}")
//    public ResponseEntity<EventosEntity> updateFile(@PathVariable Long idEvento, @RequestParam String type, @RequestParam String pathfile, @RequestParam(name="file") MultipartFile file){
//        HttpHeaders headers = new HttpHeaders();
//
//        try{
//            EventosEntity data = eventosService.findById(idEvento);
//            if("cover".equals(type)){
//                System.out.println(pathfile);
//                data.setPathfileCover(pathfile);
//            }
//            else if("video".equals(type)){
//                System.out.println(pathfile);
//                data.setPathfileVideo(pathfile);
//            }
//            eventosService.update(data);
//
//            MinIO minio = new MinIO();
//            minio.updateObject(pathfile, file);
//
//            headers.set("200", "Consulta exitosa");
//
//            return new ResponseEntity<>(data,headers,HttpStatus.OK);
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
