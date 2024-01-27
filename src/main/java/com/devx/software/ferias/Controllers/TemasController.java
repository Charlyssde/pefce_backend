package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Capacitacion.TemasEntity;
import com.devx.software.ferias.Services.Capacitacion.TemasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temas")
public class TemasController {

    private final TemasService temasService;

    @Autowired
    public TemasController(TemasService temasService) {
        this.temasService = temasService;
    }

    @PostMapping("/create")
    public ResponseEntity<TemasEntity> create(@RequestBody TemasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TemasEntity responseData = temasService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TemasEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TemasEntity responseData = temasService.findById(id);
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

    @GetMapping("/findByModuloId/{id}")
    public ResponseEntity<List<TemasEntity>> findByModuloId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<TemasEntity> responseData = temasService.findByModuloId(id);
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
    public ResponseEntity<List<TemasEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<TemasEntity> responseData = temasService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<TemasEntity> update(@RequestBody TemasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TemasEntity responseData = temasService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<TemasEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            TemasEntity responseData = temasService.findById(id);
            if (responseData != null) {
                temasService.deleteById(id);
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
