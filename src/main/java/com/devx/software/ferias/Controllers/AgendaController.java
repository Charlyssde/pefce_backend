package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Agenda.AgendaDTO;
import com.devx.software.ferias.DTOs.Agenda.AgendaListRequest;
import com.devx.software.ferias.DTOs.Agenda.AgendaRequest;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Services.Agenda.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/agenda")
@PreAuthorize("isAuthenticated()")
public class AgendaController {
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(
            AgendaService agendaService
    ) {
        this.agendaService = agendaService;
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<AgendaDTO>> pageAgenda(
            @RequestParam(required = true) Date startDate,
            @RequestParam(required = true) Date endDate,
            @RequestParam(required = true) Boolean owner,
            @RequestParam(required = false) Long ownerId
    ) {
        try {
            AgendaListRequest agendaListRequest = new AgendaListRequest(startDate, endDate, owner, ownerId);
            this.httpHeaders.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.agendaService.pageAgenda(agendaListRequest), this.httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"", "/", "/create"}, method = RequestMethod.POST)
    public ResponseEntity<AgendaEntity> create(@RequestBody AgendaRequest agendaRequest) {
        try {
            this.httpHeaders.set("200", "Registro exitoso");
            return new ResponseEntity<>(this.agendaService.create(agendaRequest), this.httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{agendaId}")
    public ResponseEntity<AgendaEntity> update(
            @PathVariable Long agendaId,
            @RequestBody AgendaRequest agendaRequest
    ) {
        try {
            this.httpHeaders.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.agendaService.update(agendaId, agendaRequest), this.httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{agendaId}")
    public ResponseEntity<HashMap<String, Object>> delete(@PathVariable Long agendaId) {
        try {
            this.httpHeaders.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.agendaService.delete(agendaId), this.httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
