package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.UsuarioPreguntaEntity;
import com.devx.software.ferias.Repositories.Capacitacion.UsuarioPreguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioPreguntaService {
    private final UsuarioPreguntaRepository usuarioPreguntaRepository;

    public UsuarioPreguntaService(UsuarioPreguntaRepository usuarioPreguntaRepository) {
        this.usuarioPreguntaRepository = usuarioPreguntaRepository;
    }

    public UsuarioPreguntaEntity save(UsuarioPreguntaEntity entity) throws Exception {
        try {
            return usuarioPreguntaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioPreguntaEntity> saveAll(List<UsuarioPreguntaEntity> entities) throws Exception {
        try {
            return usuarioPreguntaRepository.saveAll(entities);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioPreguntaEntity findByPreguntaIdAndUsuarioId(Long idPregunta, Long idUsuario) {
        return usuarioPreguntaRepository.findByPreguntaIdAndUsuarioId(idPregunta, idUsuario);
    }
}
