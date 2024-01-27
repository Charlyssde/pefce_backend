package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
//import com.devx.software.ferias.Services.Notifications.NotificationsCauService;
import com.devx.software.ferias.Services.Notifications.NotificationsService;
//import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    private final NotificationsService notificationsService;
    /*
    private final CanalesService canalesService;

    private final NotificationsCauService cauService;

    private final CatalogoAccionesService catalogoAccionesService;

    private final CanalPerfilService canalPerfilService;

    private final CanalUserService canalUserService;

    private final UserService UserService;
    */
    @Autowired
    public NotificationsController(
            NotificationsService NotificationsService
            //CanalesService canalesService,
            //CauService cauService,
            //CatalogoAccionesService catalogoAccionesService,
            //CanalPerfilService canalPerfilService,
            //CanalUserService canalUserService,
            //UserService UserService
             ) {
        this.notificationsService = NotificationsService;
        //this.canalesService = canalesService;
        //this.cauService = cauService;
        //this.catalogoAccionesService = catalogoAccionesService;
        //this.canalPerfilService = canalPerfilService;
        //this.canalUserService = canalUserService;
        //this.UserService = UserService;
    }

    @PostMapping("/setasreaded")
    public ResponseEntity<NotificationsEntity> setAsReaded(@RequestBody NotificationsEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            entity.setVista(true);
            NotificationsEntity responseData = notificationsService.update(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Start Notificaciones Region
    @GetMapping("/getLastNotifications/{number}")
    public ResponseEntity<List<NotificationsEntity>> getLastNotifications(@PathVariable Long number) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.notificationsService.getLastNotifications(number), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Start Canal Region
    /*@PostMapping("/canales/create")
    public ResponseEntity<CanalesEntity> createCanal(@RequestBody CanalesEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalesEntity responseData = canalesService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/canales/findById/{id}")
    public ResponseEntity<CanalesEntity> findCanalById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalesEntity responseData = canalesService.findById(id);
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

    @GetMapping("/canales/page")
    public ResponseEntity<List<CanalesEntity>> pageCanales() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CanalesEntity> responseData = canalesService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/canales/update")
    public ResponseEntity<CanalesEntity> updateCanal(@RequestBody CanalesEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalesEntity responseData = canalesService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/canales/deleteById/{id}")
    public ResponseEntity<CanalesEntity> deleteCanal(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalesEntity responseData = canalesService.findById(id);
            if (responseData != null) {
                canalesService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    //End Canal Region

    //Start CanalPerfil Region
    /*@GetMapping("/canales/perfiles/ByCanalId/{id}")
    public ResponseEntity<List<PerfilesEntity>> findPerfilesByCanalId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PerfilesEntity> responseData = canalPerfilService.findPerfilesByCanalId(id);
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

    @GetMapping("/canales/perfiles/ByCanalIdNull/{id}")
    public ResponseEntity<List<PerfilesEntity>> findPerfilesByCanalIdNull(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PerfilesEntity> responseData = canalPerfilService.findPerfilesByCanalIdNull(id);
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

    @PostMapping("/canales/perfiles/create")
    public ResponseEntity<CanalPerfilEntity> create(@RequestBody CanalPerfilEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalPerfilEntity responseData = canalPerfilService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/canales/perfiles/deleteByCanalIdAndPerfilId/{idCanal}/{idPerfil}")
    public ResponseEntity<CanalPerfilEntity> deleteByCanalIdAndPerfilId(@PathVariable Long idCanal, @PathVariable Long idPerfil) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalPerfilEntity responseData = canalPerfilService.findByCanalIdAndPerfilId(idCanal, idPerfil);
            if (responseData != null) {
                canalPerfilService.deleteByCanalIdAndPerfilId(idCanal, idPerfil);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    //End CanalPerfil Region

    //Start CanalUsuario Region
    /*@GetMapping("/canales/usuarios/ByCanalId/{id}")
    public ResponseEntity<List<UserEntity>> findUsuariosByCanalId(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UserEntity> responseData = canalUserService.findUsuariosByCanalId(id);
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

    @GetMapping("/canales/usuarios/ByCanalIdNull/{id}")
    public ResponseEntity<List<UserEntity>> findUsuariosByCanalIdNull(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UserEntity> responseData = canalUserService.findUsuariosByCanalIdNull(id);
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

    @PostMapping("/canales/usuarios/create")
    public ResponseEntity<CanalUsuarioEntity> create(@RequestBody CanalUsuarioEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalUsuarioEntity responseData = canalUserService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/canales/usuarios/deleteByCanalIdAndUsuarioId/{idCanal}/{idUsuario}")
    public ResponseEntity<CanalUsuarioEntity> deleteByCanalIdAndUsuarioId(@PathVariable Long idCanal, @PathVariable Long idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CanalUsuarioEntity responseData = canalUserService.findByCanalIdAndUsuarioId(idCanal, idUsuario);
            if (responseData != null) {
                canalUserService.deleteByCanalIdAndUsuarioId(idCanal, idUsuario);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    //End CanalUsuario Region

    //Start CAU Region
    /*@PostMapping("/cau/create")
    public ResponseEntity<CauEntity> createCau(@RequestBody CauEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CauEntity responseData = cauService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cau/findById/{id}")
    public ResponseEntity<CauEntity> findCauById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CauEntity responseData = cauService.findById(id);
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

    @GetMapping("/cau/page")
    public ResponseEntity<List<CauEntity>> pageCaus() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CauEntity> responseData = cauService.page();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cau/update")
    public ResponseEntity<CauEntity> updateCau(@RequestBody CauEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CauEntity responseData = cauService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cau/deleteById/{id}")
    public ResponseEntity<CauEntity> deleteCau(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CauEntity responseData = cauService.findById(id);
            if (responseData != null) {
                cauService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cau/acciones/page")
    public ResponseEntity<List<CatalogoAccionesEntity>> pageAcciones() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CatalogoAccionesEntity> responseData = catalogoAccionesService.page();
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

    @GetMapping("/cau/acciones/usuarios/ByAccionIdNull/{id}")
    public ResponseEntity<List<UserEntity>> findUsuariosByAccionIdNull(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UserEntity> responseData = cauService.findUsuariosByAccionIdNull(id);
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

    @GetMapping("/cau/acciones/canales/ByAccionIdNull/{id}")
    public ResponseEntity<List<CanalesEntity>> findCanalesByAccionIdNull(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CanalesEntity> responseData = cauService.findCanalesByAccionIdNull(id);
            if (responseData != null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    //End CAU Region


    /*@GetMapping("/enviar/predeterminada/{idAccion}")
    public ResponseEntity<Boolean> sendNotificacionPredeterminada(@PathVariable Long idAccion) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogoAccionesEntity accion = catalogoAccionesService.findById(idAccion);
            List<CauEntity> cauList = cauService.findCauByAccionId(idAccion);
            for (CauEntity cau : cauList) {
                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto(accion.getAccion());
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                //Tipo 1: Automatica para usuario
                //Tipo 2: Automatica para canal
                //Tipo 3: Memorandum para usuario
                //Tipo 4: Memorandum para canal                
                switch (cau.getTipo().intValue()) {
                    case 1:
                        ne.setTipo(Long.valueOf(1));
                        ne.setDestinatario(cau.getDestinatario());
                        break;
                    case 2:
                        ne.setTipo(Long.valueOf(2));
                        ne.setCanal(cau.getCanal());
                        break;
                    default:
                        throw new AssertionError();
                }
                this.notificationsService.create(ne);
            }
            headers.set("200", "Notificaciones enviadas");
            return new ResponseEntity<>(Boolean.TRUE, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enviar/predeterminada/modificada")
    public ResponseEntity<Boolean> sendNotificacionPredeterminadaTextoAdicional(@RequestBody Map<String, String> json) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogoAccionesEntity accion = catalogoAccionesService.findById(Long.parseLong(json.get("idAccion")));
            List<CauEntity> cauList = cauService.findCauByAccionId(Long.parseLong(json.get("idAccion")));
            for (CauEntity cau : cauList) {
                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto(accion.getAccion().concat(": ").concat(json.get("texto")));
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                //Tipo 1: Automatica para usuario
                //Tipo 2: Automatica para canal
                //Tipo 3: Memorandum para usuario
                //Tipo 4: Memorandum para canal                
                switch (cau.getTipo().intValue()) {
                    case 1:
                        ne.setTipo(Long.valueOf(1));
                        ne.setDestinatario(cau.getDestinatario());
                        break;
                    case 2:
                        ne.setTipo(Long.valueOf(2));
                        ne.setCanal(cau.getCanal());
                        break;
                    default:
                        throw new AssertionError();
                }
                this.notificationsService.create(ne);
            }
            headers.set("200", "Notificaciones enviadas");
            return new ResponseEntity<>(Boolean.TRUE, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enviar/memorandum")
    public ResponseEntity<Boolean> sendNotificacionMemorandum(@RequestBody NotificationsEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            NotificationsEntity responseData = notificationsService.create(entity);
            headers.set("200", "Notificación enviada");
            return new ResponseEntity<>(Boolean.TRUE, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/leerNotificacion/{notificacionId}")
    public ResponseEntity<NotificationsEntity> readNotification(@PathVariable Long notificacionId) {
        HttpHeaders headers = new HttpHeaders();
        try {
            NotificationsEntity notification = notificationsService.findById(notificacionId);
            notification.setVista(true);
            notification.setLeida(new Date());
            notification.setUpdatedAt(new Date());

            notificationsService.update(notification);

            headers.set("200", "Notificación enviada");
            return new ResponseEntity<>(notification, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    //End Notificaciones Region

    //Start Especial Región

    //Este método obtiene todos los correos asociados a una acción en el CAU para enviarles un correo
    //Util en el caso de que se requiera enviar un correo despues de enviar una notificación
    /*@GetMapping("/listar/correos/poraccion/{idAccion}")
    public ResponseEntity<List<String>> listCorreosByIdAccion(@PathVariable Long idAccion) {
        List<String> correos = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CauEntity> cauList = cauService.findCauByAccionId(idAccion);
            for (CauEntity cau : cauList) {
                switch (cau.getTipo().intValue()) {
                    case 1:
                        if (!correos.contains(cau.getDestinatario().getCorreo())) {
                            correos.add(cau.getDestinatario().getCorreo());
                        }
                        break;
                    case 2:
                        switch (cau.getCanal().getTipo().intValue()) {
                            case 1:
                                List<PerfilesEntity> lcpe = canalPerfilService.findPerfilesByCanalId(cau.getCanal().getId());
                                for (PerfilesEntity pe : lcpe) {
                                    List<UserEntity> ues = UserService.findAllByPerfilUsuarioId(pe.getId());
                                    for (UserEntity ue : ues) {
                                        if (!correos.contains(ue.getCorreo())) {
                                            correos.add(ue.getCorreo());
                                        }
                                    }
                                }
                                break;
                            case 2:
                                List<UserEntity> ues = canalUserService.findUsuariosByCanalId(cau.getCanal().getId());
                                for (UserEntity ue : ues) {
                                    if (!correos.contains(ue.getCorreo())) {
                                        correos.add(ue.getCorreo());
                                    }
                                }
                                break;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            headers.set("200", "Correos encontrados");
            return new ResponseEntity<>(correos, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
