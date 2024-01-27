package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Services.Events.EventsService;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;
//import com.devx.software.ferias.Entities.Capacitacion.DirectorioEmpresarialEntity;
import com.devx.software.ferias.Services.Capacitacion.CapacitacionesService;
import com.devx.software.ferias.Services.Capacitacion.ContactosService;
//import com.devx.software.ferias.z_services.DirectorioEmpresarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    //private final DirectorioEmpresarialService directorioEmpresarialService;
    private final CapacitacionesService capacitacionesService;
    private final ContactosService contactosService;
    private final EventsService eventsService;

    @Autowired
    public ExcelController(
            //DirectorioEmpresarialService directorioEmpresarialService,
            CapacitacionesService capacitacionesService,
            ContactosService contactosService,
            EventsService eventsService
    ) {
        //this.directorioEmpresarialService = directorioEmpresarialService;
        this.capacitacionesService = capacitacionesService;
        this.contactosService = contactosService;
        this.eventsService = eventsService;
    }

    @GetMapping("/page/capacitaciones")
    public ResponseEntity<List<CapacitacionesEntity>> getPageCapacitaciones() {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            List<CapacitacionesEntity> returnedData = capacitacionesService.page();
            return new ResponseEntity<>(returnedData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/eventos")
    public ResponseEntity<List<EventEntity>> getPageEventos() {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            List<EventEntity> returnedData = eventsService.page();
            return new ResponseEntity<>(returnedData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*

    @GetMapping("/page/directorios")
    public ResponseEntity<List<DirectorioEmpresarialEntity>> getPageDirectorios() {

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            List<DirectorioEmpresarialEntity> returnedData = directorioEmpresarialService.page();
            return new ResponseEntity<>(returnedData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/page/contactos")
    public ResponseEntity<List<ContactosEntity>> getPageContactos() {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            List<ContactosEntity> returnedData = contactosService.page();
            return new ResponseEntity<>(returnedData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


*/
}
