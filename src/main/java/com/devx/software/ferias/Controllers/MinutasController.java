package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Minutas.MinutasEntity;

import com.devx.software.ferias.Services.Minutas.*;
import com.devx.software.ferias.Services.Tasks.TasksService;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/minutas")
public class MinutasController {

    private final MinutasService minutasService;

    private final ContactoMinutaService contactoMinutaService;

    private final MinutaTareaService minutaTareaService;

    private final UserService userService;

    private final MinutaTemaService minutaTemaService;

    private final MinutaArchivoService minutaArchivoService;

    private final UsuarioMinutaService usuarioMinutaService;

    private final MinutasTareasService mTareasService;
   
    private  final TasksService tasksService;
    @Autowired
    public MinutasController(
            MinutasService minutasService,
            ContactoMinutaService contactoMinutaService,
            MinutaTareaService minutaTareaService,
            UserService userService,
            MinutaTemaService minutaTemaService,
            MinutaArchivoService minutaArchivoService,
            UsuarioMinutaService usuarioMinutaService,
            MinutasTareasService mTareaService,
            TasksService tasksService
            ) {
        this.minutasService = minutasService;
        this.contactoMinutaService = contactoMinutaService;
        this.minutaTareaService = minutaTareaService;
        this.userService = userService;
        this.minutaTemaService = minutaTemaService;
        this.minutaArchivoService = minutaArchivoService;
        this.usuarioMinutaService = usuarioMinutaService;
        this.mTareasService = mTareaService;
        this.tasksService = tasksService;
    }

    @PostMapping("/create")
    public ResponseEntity<MinutasEntity> create(@RequestBody MinutasEntity entity) {
        HttpHeaders headers = new HttpHeaders();

        try {

            if (entity.getFolio().equals("")) {
                Long finFolio = minutasService.findLastIdInserted();
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                Date hoy = new Date();
                String folio = "M/".concat(sdf.format(hoy));
                finFolio = (finFolio == null) ? 0 : finFolio;
                folio = folio.concat("/".concat(Long.toString(finFolio + 1)));
                entity.setFolio(folio);
            }

            MinutasEntity responseData = minutasService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<MinutasEntity> update(@RequestBody MinutasEntity entity) {
        
        List<TaskEntity> listtareas = tasksService.findallTaskbyMinuta(entity.getId());
        
        HttpHeaders headers = new HttpHeaders();
        MinutasEntity toUpdate = new MinutasEntity ();
            toUpdate.setId(entity.getId());
            toUpdate.setAsunto(entity.getAsunto());
            toUpdate.setCiudad(entity.getCiudad());
            toUpdate.setCodigoPostal(entity.getCodigoPostal());
            toUpdate.setCreatedAt(entity.getCreatedAt());
            toUpdate.setEstado(entity.getEstado());
            toUpdate.setFecha(entity.getFecha());
            toUpdate.setFolio(entity.getFolio());
           toUpdate.setMunicipio(entity.getMunicipio());
           toUpdate.setMinutaUsuarios(entity.getMinutaUsuarios());
           toUpdate.setMinutaTemas(entity.getMinutaTemas());
           toUpdate.setPuntosTratados(entity.getPuntosTratados());
           toUpdate.setProyecto(entity.getProyecto());
           toUpdate.setSede(entity.getSede());
           toUpdate.setTipoMinuta(entity.getTipoMinuta());
           toUpdate.setCreatedAt(entity.getCreatedAt());
           toUpdate.setMinutaTareas(listtareas);
           toUpdate.setObjetivo(entity.getObjetivo());
           toUpdate.setMinutaArchivos(entity.getMinutaArchivos());//vacio
           
        try {
            MinutasEntity responseData = minutasService.update(toUpdate);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<MinutasEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            MinutasEntity responseData = minutasService.findById(id);
            if (responseData != null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<MinutasEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<MinutasEntity> responseData = minutasService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<MinutasEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            MinutasEntity responseData = minutasService.findById(id);
            if (responseData != null) {

                minutasService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Proyectos tareas
    @GetMapping("/{id}/tareas")
    public ResponseEntity<List<TaskEntity>> getTareasMinuta(
            @PathVariable Long id
    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<TaskEntity> responseData = mTareasService.findByMinutaId(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/tareas/{idUsuario}")
    public ResponseEntity<TaskEntity> createTareaMinuta(
            @PathVariable Long id,
            @PathVariable Long idUsuario,
            @RequestBody TaskEntity entity
    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TaskEntity response = mTareasService.save(entity, id, idUsuario);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/tareas/{idUsuario}")
    public ResponseEntity<TaskEntity> updateTareaMinuta(
            @PathVariable Long id,
            @PathVariable Long idUsuario,
            @RequestBody TaskEntity entity
    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TaskEntity response = mTareasService.update(entity, id, idUsuario);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
