package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.PromocionDigital.PromocionDigitalEntity;
import com.devx.software.ferias.Services.PromocionDigital.PromocionDigitalService;
import com.devx.software.ferias.DTOs.PromocionDigital.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocionDigital")
public class PromocionDigitalController {
    private final PromocionDigitalService promocionDigitalService;

    @Autowired
    public PromocionDigitalController(PromocionDigitalService promocionDigitalService) {
        this.promocionDigitalService = promocionDigitalService;
    }

    @PostMapping("/create")
    public ResponseEntity<PromocionDigitalEntity> create(@RequestBody PromocionDigitalEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PromocionDigitalEntity responseData = promocionDigitalService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PromocionDigitalEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PromocionDigitalEntity responseData = promocionDigitalService.findById(id);
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
    public ResponseEntity<List<PromocionDigitalEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PromocionDigitalEntity> responseData = promocionDigitalService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PromocionDigitalEntity> update(@RequestBody PromocionDigitalEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PromocionDigitalEntity responseData = promocionDigitalService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<PromocionDigitalEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PromocionDigitalEntity responseData = promocionDigitalService.findById(id);
            if (responseData != null) {
                promocionDigitalService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
