package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Plantillas.PlantillasEntity;
import com.devx.software.ferias.Services.Plantillas.PlantillasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantillas")
public class PlantillasController {
    private final PlantillasService plantillasService;

    @Autowired
    public PlantillasController(PlantillasService plantillasService) {
        this.plantillasService = plantillasService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlantillasEntity> create(@RequestBody PlantillasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PlantillasEntity responseData = this.plantillasService.saveT(entity);

            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<PlantillasEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PlantillasEntity> responseData = this.plantillasService.getAll();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/usuario/{id}")
    public ResponseEntity<List<PlantillasEntity>> pageByUsuario(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PlantillasEntity> responseData = this.plantillasService.getAllByUsuarioId(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PlantillasEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PlantillasEntity responseData = this.plantillasService.findById(id).get();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PlantillasEntity> update(@RequestBody PlantillasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PlantillasEntity responseData = this.plantillasService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlantillasEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PlantillasEntity responseData = this.plantillasService.findById(id).get();
            if (responseData != null) {
                this.plantillasService.delete(id);
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