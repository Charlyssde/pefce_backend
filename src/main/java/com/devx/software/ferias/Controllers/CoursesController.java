package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionContactoPageDTO;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionContactoEntity;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.Capacitacion.UsuarioCapacitacionEntity;
import com.devx.software.ferias.Mappers.Capacitacion.CapacitacionContactoMapper;
import com.devx.software.ferias.Services.Capacitacion.CapacitacionContactoService;
import com.devx.software.ferias.Services.Capacitacion.CapacitacionesService;
import com.devx.software.ferias.Services.Capacitacion.UsuarioCapacitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/capacitaciones")
@PreAuthorize("isAuthenticated()")
public class CoursesController {

    private final CapacitacionesService capacitacionesService;

    private final CapacitacionContactoService capacitacionContactoService;

    private final UsuarioCapacitacionService usuarioCapacitacionService;

    private final CapacitacionContactoMapper capacitacionContactoMapper;

    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public CoursesController(CapacitacionesService capacitacionesService, CapacitacionContactoService capacitacionContactoService, UsuarioCapacitacionService usuarioCapacitacionService, CapacitacionContactoMapper capacitacionContactoMapper) {
        this.capacitacionesService = capacitacionesService;
        this.capacitacionContactoService = capacitacionContactoService;
        this.usuarioCapacitacionService = usuarioCapacitacionService;
        this.capacitacionContactoMapper = capacitacionContactoMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<CapacitacionesEntity> create(@RequestBody CapacitacionesEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CapacitacionesEntity responseData = capacitacionesService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCapacitacionContacto")
    public ResponseEntity<CapacitacionContactoEntity> createCapacitacionContacto(@RequestBody CapacitacionContactoEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CapacitacionContactoEntity responseData = capacitacionContactoService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUsuarioCapacitacion")
    public ResponseEntity<UsuarioCapacitacionEntity> createUsuarioCapacitacion(@RequestBody UsuarioCapacitacionEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            //Long tocheck = capacitacionesService.checkIfCapacitacionRegistrada(entity.getCapacitacion().getId(), entity.getUsuario().getId());
            //if (tocheck >= 1) {
                //return new ResponseEntity("Capacitación Registrada", HttpStatus.OK);
            //}

            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelUsuarioCapacitacion")
    public ResponseEntity<UsuarioCapacitacionEntity> cancelUsuarioCapacitacion(@RequestBody UsuarioCapacitacionEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.delete(entity);
            headers.set("200", "cancelación exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<CapacitacionesEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CapacitacionesEntity responseData = capacitacionesService.findById(id);
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
    public ResponseEntity<List<CapacitacionesEntity>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CapacitacionesEntity> responseData = capacitacionesService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageContactosByIdCapacitacion/{id}")
    public ResponseEntity<List<CapacitacionContactoPageDTO>> pageContactosByIdCapacitacion(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CapacitacionContactoEntity> getPageList = capacitacionContactoService.findContactosByIdCapacitacion(id);
            List<CapacitacionContactoPageDTO> responseData = !getPageList.isEmpty() ? capacitacionContactoMapper.listEntityToPageDTO(getPageList) : new ArrayList<>();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageUsuariosByIdCapacitacion/{id}")
    public ResponseEntity<List<UsuarioCapacitacionEntity>> pageUsuariosByIdCapacitacion(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UsuarioCapacitacionEntity> responseData = usuarioCapacitacionService.findByCapacitacionId(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findCapacitacionesRegistradas/{idUsuario}")
    public ResponseEntity<List<CapacitacionesEntity>> findCapacitacionesRegistradas(@PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CapacitacionesEntity> responseData = capacitacionesService.findCapacitacionesRegistradas(idUsuario);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validarCapacitacionCompleta/{idCapacitacion}/{idUsuario}")
    public ResponseEntity<Boolean> validarCapacitacionCompleta(@PathVariable Long idCapacitacion, @PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Long preguntasCapacitacion = capacitacionesService.countPreguntasPorCapacitacion(idCapacitacion);
            Long preguntasRespondidas = capacitacionesService.countPreguntasRespondidasPorCapacitacion(idCapacitacion, idUsuario);
            if (preguntasCapacitacion.equals(preguntasRespondidas)) {
                return new ResponseEntity<>(true, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actualizaConstancia/{idContacto}")
    public ResponseEntity<UsuarioCapacitacionEntity> actualizaConstancia(@PathVariable Long idContacto) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UsuarioCapacitacionEntity> responseData = usuarioCapacitacionService.findByUsuarioId( idContacto );
            try {
                Boolean constancia  = !responseData.get(0).isConstancia();
                responseData.get(0).setConstancia( constancia );
            } catch (Exception e){
                responseData.get(0).setConstancia( true );
            }
            return new ResponseEntity<>( usuarioCapacitacionService.update( responseData.get(0) ) , headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findCapacitacionesNoRegistradas/{idUsuario}")
    public ResponseEntity<List<CapacitacionesEntity>> findCapacitacionesNoRegistradas(@PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CapacitacionesEntity> responseData = capacitacionesService.findCapacitacionesNoRegistradas(idUsuario);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findUsuarioCapacitacionByUuid/{uuid}")
    public ResponseEntity<UsuarioCapacitacionEntity> findUsuarioCapacitacionByUuid(@PathVariable String uuid) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.findByUuidFinalizado(uuid);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/usuarioCapacitacion/findByCapacitacionIdAndUsuarioId")
    public ResponseEntity<UsuarioCapacitacionEntity> findByCapacitacionIdAndUsuarioId(@RequestBody Map<String, Long> json) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.findByCapacitacionIdAndUsuarioId(json.get("idCapacitacion"), json.get("idUsuario"));
            headers.set("200", "Busqueda exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CapacitacionesEntity> update(@RequestBody CapacitacionesEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CapacitacionesEntity responseData = capacitacionesService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarioCapacitacion/update")
    public ResponseEntity<UsuarioCapacitacionEntity> updateUsuarioCapacitacion(@RequestBody UsuarioCapacitacionEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarioCapacitacion/finalizarCapacitacion")
    public ResponseEntity<UsuarioCapacitacionEntity> finalizarCapacitacionUsuarioCapacitacion(@RequestBody UsuarioCapacitacionEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        entity.setConcluyo(true);
        entity.setUuidFinalizado(UUID.randomUUID().toString());
        try {
            UsuarioCapacitacionEntity responseData = usuarioCapacitacionService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<CapacitacionesEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CapacitacionesEntity responseData = capacitacionesService.findById(id);
            if (responseData != null) {
                capacitacionesService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reportesolicitudes")
    public ResponseEntity<HashMap<String, Object>> reporteSolicitudes() {
        HttpHeaders headers = new HttpHeaders();
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            formResources.put("sexo", this.capacitacionesService.obtenerTotalPorSexo());
            formResources.put("municipio", this.capacitacionesService.obtenerTotalPorMunicipio());
            formResources.put("sector", this.capacitacionesService.obtenerTotalPorsector());
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findCapacitacionesByIdUsuario/{idUsuario}")
    public ResponseEntity<List<UsuarioCapacitacionEntity>> getCapacitacionesByIdUsuario(@PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();

        try {
            List<UsuarioCapacitacionEntity> responseData = usuarioCapacitacionService.findByUsuarioId(idUsuario);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
