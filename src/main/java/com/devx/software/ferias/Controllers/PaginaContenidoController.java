package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Services.Events.EventsService;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.PaginaContenido.PaginaContenidoEntity;
import com.devx.software.ferias.Services.Capacitacion.CapacitacionesService;
//import com.devx.software.ferias.Services.DirectorioEmpresarialService;
import com.devx.software.ferias.Services.PaginaContenido.PaginaContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/paginaContenido")
@PreAuthorize("permitAll()")
public class PaginaContenidoController {

    //@Autowired
    //DirectorioEmpresarialService empresasService;

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    EventsService eventsService;

    @Autowired
    CapacitacionesService capacitacionesService;

    @Autowired
    PaginaContenidoService service;

    @GetMapping("/getContent/{page}")
    public ResponseEntity<PaginaContenidoEntity> getContent(@PathVariable String page) {
        HttpHeaders headers = new HttpHeaders();

        try {
            PaginaContenidoEntity response = service.findPaginaContenidoByPagina(page);
            return new ResponseEntity(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @GetMapping("/getPefceData")
//    public ResponseEntity<HashMap> getPefceData() {
//        HttpHeaders headers = new HttpHeaders();
//
//        try {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("empresas", empresasService.page());
//            map.put("proyectos", projectsRepository.findAll());
//            map.put("eventos", eventsService.page());
//            map.put("capacitaciones", capacitacionesService.page());
//
//            return new ResponseEntity(map, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/updateContent/{page}")
    public ResponseEntity<PaginaContenidoEntity> updateContent(@PathVariable String page, @RequestBody String content) {
        HttpHeaders headers = new HttpHeaders();

        try {
            PaginaContenidoEntity data = service.findPaginaContenidoByPagina(page);
            data.setContenido(content);
            service.update(data);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity(data, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEventos")
    public ResponseEntity<List<EventEntity>> getEventos() {
        HttpHeaders headers = new HttpHeaders();

        try {
            List<EventEntity> resp = eventsService.page();
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity(resp, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCapacitaciones")
    public ResponseEntity<List<CapacitacionesEntity>> getCapacitaciones() {
        HttpHeaders headers = new HttpHeaders();

        try {
            List<CapacitacionesEntity> resp = capacitacionesService.page();
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity(resp, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
