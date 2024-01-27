package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Capacitacion.UsuarioCapacitacionEntity;
import com.devx.software.ferias.Repositories.Capacitacion.UsuarioCapacitacionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.devx.software.ferias.Services.Users.UserService;

import java.util.HashMap;
import java.util.List;

@Service
public class UsuarioCapacitacionService {
    private final UsuarioCapacitacionRepository usuarioCapacitacionRepository;
    private final UserService userService;
    public UsuarioCapacitacionService(UsuarioCapacitacionRepository usuarioCapacitacionRepository, UserService userService) {
        this.usuarioCapacitacionRepository = usuarioCapacitacionRepository;
        this.userService = userService;
    }

    public UsuarioCapacitacionEntity create(UsuarioCapacitacionEntity entity) throws Exception {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( email );
            entity.setUsuario( usuario );

            return usuarioCapacitacionRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioCapacitacionEntity> findByUsuarioId(Long idUsuario) {
        return usuarioCapacitacionRepository.findByUsuarioId(idUsuario);
    }

    public UsuarioCapacitacionEntity findByCapacitacionIdAndUsuarioId(Long idCapacitacion, Long idUsuario) {
        return usuarioCapacitacionRepository.findByCapacitacionIdAndUsuarioId(idCapacitacion, idUsuario);
    }

    public UsuarioCapacitacionEntity findByUuidFinalizado(String uuid) {
        return usuarioCapacitacionRepository.findByUuidFinalizado(uuid);
    }

    public UsuarioCapacitacionEntity update(UsuarioCapacitacionEntity entity) {
        return usuarioCapacitacionRepository.save(entity);
    }

    public List<UsuarioCapacitacionEntity> findByCapacitacionId(Long id) {
        return usuarioCapacitacionRepository.findByCapacitacionId(id);
    }

    public UsuarioCapacitacionEntity delete(UsuarioCapacitacionEntity usuarioCapacitacion) throws Exception {
        if (usuarioCapacitacion != null) {
            this.usuarioCapacitacionRepository.deleteById( usuarioCapacitacion.getId() );
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Registro en la capacitación eliminado");
            return usuarioCapacitacion;
        }
        throw new Exception("El evento de capacitación no existe");
    }

}
