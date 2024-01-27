package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Capacitacion.ModulosEntity;
import com.devx.software.ferias.Services.Capacitacion.ModulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModulosController {

    private final ModulosService modulosService;

    @Autowired
    public ModulosController(ModulosService modulosService) {
        this.modulosService = modulosService;
    }

    @PostMapping("/create")
    public ResponseEntity<ModulosEntity> create(@RequestBody ModulosEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            ModulosEntity responseData = modulosService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ModulosEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            ModulosEntity responseData = modulosService.findById(id);
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

    @GetMapping("/findByCapacitacionId/{id}")
    public ResponseEntity<List<ModulosEntity>> findByCapacitacionId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<ModulosEntity> responseData = modulosService.findByCapacitacionId(id);
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
    public ResponseEntity<List<ModulosEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<ModulosEntity> responseData = modulosService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ModulosEntity> update(@RequestBody ModulosEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            ModulosEntity responseData = modulosService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ModulosEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            ModulosEntity responseData = modulosService.findById(id);
            if (responseData != null) {
                modulosService.deleteById(id);
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
