package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Repositories.Shared.SharedRepository;
import com.devx.software.ferias.Services.Capacitacion.CapacitacionesService;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.Services.Events.EventsService;
import com.devx.software.ferias.Services.Notifications.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("shared")
@PreAuthorize("permitAll()")
public class SharedController {

    private final CatalogsService catalogsService;
    private final EventsService eventsService;
    private final CapacitacionesService capacitacionesService;

    private final SharedRepository sharedRepository;

    @Autowired
    public SharedController(
            CatalogsService catalogsService,
            EventsService eventsService,
            CapacitacionesService capacitacionesService,
            SharedRepository sharedRepository
    ) {
        this.catalogsService = catalogsService;
        this.eventsService = eventsService;
        this.capacitacionesService = capacitacionesService;
        this.sharedRepository = sharedRepository;
    }

    @GetMapping("/homepage-data")
    public ResponseEntity<HashMap<String, Object>> homepageData(){
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();
            response.put("eventos",this.eventsService.getLastTenEvents());
            response.put("capacitaciones",this.capacitacionesService.getLastTenCourses());

            headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-pefce-numbers")
    public ResponseEntity<List<?>> getAllPefceNumbers(){
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.sharedRepository.getAllPefceNumbers(), headers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showEnterpriseRegistration")
    public ResponseEntity<?> showEnterpriseRegistration() {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();
            response.put("categorias", this.catalogsService.getAllCatalogosByTipoCatalogo("TIPO_EMPRESA"));
            response.put("regimenesFiscales", this.catalogsService.getAllCatalogosByTipoCatalogo("REGIMEN_FISCAL"));
            response.put("sectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SECTORES"));
            response.put("subsectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SUBSECTORES"));

            headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
