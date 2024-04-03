/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import com.devx.software.ferias.Services.Capacitacion.PreguntasService;
import com.devx.software.ferias.Services.Preguntas.PreguntaService;
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
@RequestMapping("/pregunta")
public class PreguntaController {

    private final PreguntaService preguntaService;

    @Autowired
    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Preguntas>> findAllArchivo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(preguntaService.findAllbyencuesta(id));
    }
    
    
       @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createdArchivo(@RequestBody Preguntas preguntas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(preguntaService.save(preguntas));
    }

}
