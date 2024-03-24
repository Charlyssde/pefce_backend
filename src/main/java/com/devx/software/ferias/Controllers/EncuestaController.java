/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Encuesta.EncuestasDTO;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Services.Encuestas.EncuestaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author blopez
 */
@RestController
@RequestMapping("/encuesta")
public class EncuestaController {
    
    private final EncuestaService encuestaService;

    @Autowired
    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }
    
 
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Encuestas>> findAllArchivo() {
        return ResponseEntity.status(HttpStatus.OK).body(encuestaService.findAll());
    }
    
      @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Encuestas> getArchivoById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(encuestaService.findById(id));
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createdArchivo(@RequestBody Encuestas encuestas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(encuestaService.save(encuestas));
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateArchivo(@RequestBody Encuestas encuestas ,@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(encuestaService.update(id, encuestas));
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletedArchivo(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(encuestaService.deleted(id));
    }
    
}
