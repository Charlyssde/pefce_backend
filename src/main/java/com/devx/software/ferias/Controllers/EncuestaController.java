/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Capacitacion.PreguntasDTO;
import com.devx.software.ferias.DTOs.Encuesta.EncuestasDTO;
import com.devx.software.ferias.DTOs.Encuestas.RespuestasDTO;
import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListUserFilterDTO;
import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import com.devx.software.ferias.Entities.Respuestas.Respuesta;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Enums.TipoRespuesta;
import com.devx.software.ferias.Services.Encuestas.EncuestaService;
import java.util.List;
import java.util.stream.Collectors;

import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import com.devx.software.ferias.Services.Preguntas.PreguntaService;
import com.devx.software.ferias.Services.Respuestas.RespuestasService;
import com.devx.software.ferias.Services.Users.UserService;
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
    private final EnterprisesService enterprisesService;
    private final RespuestasService respuestasService;
    private final PreguntaService preguntaService;
    private final UserService userService;

    @Autowired
    public EncuestaController(EncuestaService encuestaService, EnterprisesService enterprisesService, RespuestasService respuestasService, PreguntaService preguntaService, UserService userService) {
        this.encuestaService = encuestaService;
        this.enterprisesService = enterprisesService;
        this.respuestasService = respuestasService;
        this.preguntaService = preguntaService;
        this.userService = userService;
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

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/encuentas", method = RequestMethod.GET)
    public ResponseEntity<?> getEncuentas(@PathVariable Long id) throws Exception {
        EnterpriseListUserFilterDTO enterpriseEntity = this.enterprisesService.findByUserId(id);
        EnterpriseEntity enterprise = this.enterprisesService.findById(enterpriseEntity.getId());
        return ResponseEntity.status(HttpStatus.OK).body(this.encuestaService.findAllByEnterpriseEntityId(enterprise));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/encuentas/{idEncuesta}", method = RequestMethod.GET)
    public ResponseEntity<?> getEncuentaRespuestas(@PathVariable Long id, @PathVariable Long idEncuesta) throws Exception {
        EnterpriseListUserFilterDTO enterpriseEntity = this.enterprisesService.findByUserId(id);
        EnterpriseEntity enterprise = this.enterprisesService.findById(enterpriseEntity.getId());
        Encuestas encuesta = this.encuestaService.findById(idEncuesta);
        return ResponseEntity.status(HttpStatus.OK).body(this.respuestasService.findAllByEncuestaAndEmpresa(encuesta, enterprise));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/encuentas/{idEncuesta}", method = RequestMethod.POST)
    public ResponseEntity<?> saveEncuentaRespuestas(@PathVariable Long id, @PathVariable Long idEncuesta, @RequestBody List<RespuestasDTO> respuestas) throws Exception {
        EnterpriseListUserFilterDTO enterpriseEntity = this.enterprisesService.findByUserId(id);
        EnterpriseEntity enterprise = this.enterprisesService.findById(enterpriseEntity.getId());
        Encuestas encuesta = this.encuestaService.findById(idEncuesta);
        UserEntity user = this.userService.findUserById(id);

        System.out.println(respuestas.get(0).toString());
        System.out.println(id.toString());
        System.out.println(idEncuesta.toString());

        this.respuestasService.deleteAllByEncuestaAndEmpresa(encuesta, enterprise);

        List<Respuesta> respuestasList = respuestas.stream().map(respuesta -> {
            Preguntas pregunta = this.preguntaService.findById(respuesta.getPregunta());
            Respuesta respuestaEntity = new Respuesta();
            respuestaEntity.setRespuesta(TipoRespuesta.values()[(int) respuesta.getRespuesta() - 1]);
            respuestaEntity.setPregunta(pregunta);
            respuestaEntity.setEmpresa(enterprise);
            respuestaEntity.setEncuesta(encuesta);
            respuestaEntity.setCreadoPor(user);
            return respuestaEntity;
        }).collect(Collectors.toList());

        this.respuestasService.saveAll(respuestasList);

        return ResponseEntity.status(HttpStatus.OK).body(respuestas);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/evento/{id}", method = RequestMethod.GET)
    public ResponseEntity<Encuestas> getEncuestaDTO(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(encuestaService.findById(id));
    }
}
