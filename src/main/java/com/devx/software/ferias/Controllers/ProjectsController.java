package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Projects.ProjectRequest;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Services.Notifications.NotificationsService;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Mappers.Projects.ProjectListMapper;
import com.devx.software.ferias.Repositories.Projects.ProjectsColaboratorsRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsHistoryRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.Services.Projects.ProjectsService;
import com.devx.software.ferias.Services.Plantillas.PlantillasService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/projects")
@PreAuthorize("isAuthenticated()")
public class ProjectsController {
    private final HttpHeaders headers = new HttpHeaders();

    private final ProjectsService projectsService;
    private final ProjectsRepository projectsRepository;
    private final UserService usuariosService;
    private final UserService userService;
    private final CatalogsService catalogsService;

    private final ProjectListMapper projectListMapper;

    private final PlantillasService plantillasService;
    private final ProjectsHistoryRepository projectsHistoryRepository;
    private final ProjectsColaboratorsRepository projectsColaboratorsRepository;
    private final SistemaRepository sistemaRepository;
    private final NotificationsService notificationsService;

    @Autowired
    public ProjectsController(
            ProjectsService projectsService,
            ProjectsRepository projectsRepository,
            UserService usuariosService,
            UserService userService, ProjectsHistoryRepository projectsHistoryRepository,
            ProjectsColaboratorsRepository projectsColaboratorsRepository,
            CatalogsService catalogsService,
            PlantillasService plantillasService,
            ProjectListMapper projectListMapper,
            SistemaRepository sistemaRepository, NotificationsService notificationsService) {
        this.projectsService = projectsService;
        this.projectsRepository = projectsRepository;

        this.userService = userService;

        this.projectsHistoryRepository = projectsHistoryRepository;
        this.projectsColaboratorsRepository = projectsColaboratorsRepository;
        this.usuariosService = usuariosService;
        this.catalogsService = catalogsService;
        this.plantillasService = plantillasService;
        this.projectListMapper = projectListMapper;
        this.sistemaRepository = sistemaRepository;
        this.notificationsService = notificationsService;
    }


    @RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
    public ResponseEntity<ProjectEntity> create(@RequestBody ProjectRequest projectRequest) {
        HttpHeaders headers = new HttpHeaders();
        try {
            this.headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(
                    this.projectsService.create(projectRequest),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = { "/", "", "/all" }, method = RequestMethod.GET)
    public ResponseEntity<List<ProjectEntity>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<ProjectEntity> getPageList = this.projectsService.getAll();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(getPageList, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PaginationResponse> page(
            // Pagination parameters
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // Global specific filters
            @RequestParam(required = false) String filtro,
            @RequestParam(required = false) String activo,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            // User|Profile filters
            @RequestParam(required = false) String perfil,
            @RequestParam(required = false) String usuario,
            // Module specific filters
            @RequestParam(required = false) String prioridad,
            @RequestParam(required = false) String estatus,
            @RequestParam(required = false) String conResponsable,
            // Enterprise specific filters
            @RequestParam(required = false) String regimenFiscal,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String subsector,
            @RequestParam(required = false) String empresa,
            // Institution specific filters
            @RequestParam(required = false) String area,
            @PathVariable Long id
    ){
        try {
System.out.println("P-a");
            this.headers.set("200", "Consulta exitosa");
            Pageable pageable = PageRequest.of(page, size);
            Page<ProjectEntity> pageDataset = this.projectsService.pageProjects(
                    pageable,
                    filtro,
                    activo,
                    fechaInicio,
                    fechaFin,
                    usuario,
                    perfil,
                    prioridad,
                    estatus,
                    conResponsable,
                    regimenFiscal,
                    categoria,
                    sector,
                    subsector,
                    empresa,
                    area,
                    id
            );
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.projectListMapper.listEntityToDTO(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            System.out.println("P-b");
            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> findById(@PathVariable Long id) {
        try {
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity<>(this.projectsService.findById(id),this.headers,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectEntity> update(@PathVariable Long id, @RequestBody ProjectRequest projectRequest) {
        try {
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity<>(
                    this.projectsService.update(projectRequest,id),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}/panel")
    public ResponseEntity<ProjectEntity> updateByPanel(@PathVariable Long id, @RequestBody ProjectEntity projectEntity) {
        System.out.println("A");
        try {
            projectEntity.setTipoId(
                    catalogsService.getCatalogByTipoAndNombre(
                            "ESTATUS_PROYECTOS",
                            projectEntity.getEstatus()
                    )
            );
            System.out.println(projectEntity.getTipoId().getId());
            if( projectEntity.getTipoId().getId() == 76 ) {
                System.out.println("B");
                Mailgun mailgun = new Mailgun();
                mailgun.sendBasicEmail(projectEntity.getNombre() + " - Encuesta de satisfacción", projectEntity.getEmpresaId().getEmail(), "Le solicitamos ingrese a nuestra platforma y llene la encuesta de satisfacción que puede descargar en el apartado del proyecto: " + projectEntity.getNombre() + " , por favor descargue, la llene y la firme, para hacerla llegar al correo del responsable del seguimiento de su proyecto. ");

                try {

                    List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
                    UserEntity responsable = sistemaEntity.get(0).getResponableDce();

                    NotificationsEntity ne = new NotificationsEntity();
                    ne.setTexto("Se ha finalizado el proyecto:" + projectEntity.getEmpresaId().getEmail() + " y se envió por correo electrónico al encuesta de satisfacción a los participantes registrados.");
                    ne.setVista(Boolean.FALSE);
                    ne.setCreatedAt(new Date());
                    ne.setTipo(Long.valueOf(1));
                    ne.setDestinatario(responsable);
                    this.notificationsService.create(ne);

                } catch (Exception e) {
                    return null;
                }
            }
            System.out.println("C");
            this.headers.set("200","Actualización exitosa");

            return new ResponseEntity<>( this.projectsService.updateByPanel(projectEntity,id), this.headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Scopes
    @GetMapping("/getLastActiveProjects/{number}")
    public ResponseEntity<List<ProjectEntity>> getLastActiveProjects(@PathVariable Long number){
        try {
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity<>(
                    this.projectsService.getLastActiveProjects(number),
                    this.headers,
                    HttpStatus.OK
            );
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
            formResources.put("sexo", this.projectsService.obtenerTotalPorSexo());
            formResources.put("municipio", this.projectsService.obtenerTotalPorMunicipio());
            formResources.put("sector", this.projectsService.obtenerTotalPorsector());

            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstatusId/{id}")
    public ResponseEntity<List<ProjectEntity>> findByTipoId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {

            List<ProjectEntity> responseData = projectsService.findByTipoId(id);

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

    @PostMapping("/enviarcorreo")
    public ResponseEntity<EventEntity> enviarcorreo(
            @RequestParam(name = "correo") String correo,
            @RequestParam(name = "mensaje") String mensaje

    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Mailgun mailgun = new Mailgun();
            try {
                mailgun.sendBasicEmail("Reunión virtual PEFCE", correo , mensaje );

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

}
