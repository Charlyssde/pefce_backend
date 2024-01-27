package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import com.devx.software.ferias.Entities.Capacitacion.UsuarioPreguntaEntity;
import com.devx.software.ferias.Services.Capacitacion.PreguntasService;
import com.devx.software.ferias.Services.Capacitacion.UsuarioPreguntaService;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/preguntas")
public class PreguntasController {
    private final PreguntasService preguntasService;
    private final UserService userService;

    private final UsuarioPreguntaService usuarioPreguntaService;
    private UserEntity usuario_id;

    @Autowired
    public PreguntasController(PreguntasService preguntasService, UserService userService, UsuarioPreguntaService usuarioPreguntaService) {
        this.preguntasService = preguntasService;
        this.userService = userService;
        this.usuarioPreguntaService = usuarioPreguntaService;
    }

    @PostMapping("/create")
    public ResponseEntity<PreguntasEntity> create(@RequestBody PreguntasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PreguntasEntity responseData = preguntasService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUsuarioPreguntas")
    public ResponseEntity<List<UsuarioPreguntaEntity>> createCapacitacionPreguntas(@RequestBody List<UsuarioPreguntaEntity> entities) {
        HttpHeaders headers = new HttpHeaders();
        List<UsuarioPreguntaEntity> newEntities = new ArrayList<UsuarioPreguntaEntity>();
        List<UsuarioPreguntaEntity> responseData = new ArrayList<UsuarioPreguntaEntity>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);
        usuario_id = this.userService.findByEmail( email );

        try {
            for (UsuarioPreguntaEntity entity : entities) {
                entity.setUsuario( usuario_id );

                UsuarioPreguntaEntity toCheck = usuarioPreguntaService.findByPreguntaIdAndUsuarioId(entity.getPregunta().getId(), entity.getUsuario().getId());
                if (toCheck != null) {
                    //toCheck.setUsuario(usuario_id);
                    //Here we update responses that exists in DB and remove it from array of news
                    toCheck.setRespuesta(entity.getRespuesta());
                    toCheck.setUpdatedAt(entity.getCreatedAt());
                    usuarioPreguntaService.save(toCheck);
                    responseData.add(toCheck);
                    //entities.remove(entity);
                } else {
                    entity.setUsuario(usuario_id);
                    newEntities.add(entity);
                }
            }
            if (!newEntities.isEmpty()) {
                for (UsuarioPreguntaEntity entity : entities) {
                    entity.setUsuario(usuario_id);
                }
                responseData.addAll(usuarioPreguntaService.saveAll(entities));
            }
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PreguntasEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PreguntasEntity responseData = preguntasService.findById(id);
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

    @GetMapping("/findByTemaId/{id}")
    public ResponseEntity<List<PreguntasEntity>> findByTemaId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PreguntasEntity> responseData = preguntasService.findByTemaId(id);
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

    @GetMapping("/findByPreguntaIdAndUsuarioId/{idPregunta}/{idUsuario}")
    public ResponseEntity<UsuarioPreguntaEntity> findByPreguntaIdAndUsuarioId(@PathVariable Long idPregunta, @PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuarioPreguntaEntity responseData = usuarioPreguntaService.findByPreguntaIdAndUsuarioId(idPregunta, idUsuario);
//            if (responseData != null) {
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
//            } else {
//                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
//            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<PreguntasEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PreguntasEntity> responseData = preguntasService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PreguntasEntity> update(@RequestBody PreguntasEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PreguntasEntity responseData = preguntasService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<PreguntasEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            PreguntasEntity responseData = preguntasService.findById(id);
            if (responseData != null) {
                preguntasService.deleteById(id);
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
