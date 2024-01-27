package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.UsuarioMinutaEntity;
import com.devx.software.ferias.Repositories.Minutas.UsuarioMinutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMinutaService {

    private final UsuarioMinutaRepository usuarioMinutaRepository;

    @Autowired
    public UsuarioMinutaService(UsuarioMinutaRepository usuarioMinutaRepository) {
        this.usuarioMinutaRepository = usuarioMinutaRepository;
    }

    public UsuarioMinutaEntity create(UsuarioMinutaEntity entity) throws Exception {
        try {
            return usuarioMinutaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioMinutaEntity> page() {
        return usuarioMinutaRepository.findAll();
    }

    public UsuarioMinutaEntity findById(Long id) {
        return usuarioMinutaRepository.findUsuarioMinutaById(id);
    }

    public UsuarioMinutaEntity update(UsuarioMinutaEntity entity) {
        return usuarioMinutaRepository.save(entity);
    }

    public void deleteById(Long id) {
        usuarioMinutaRepository.deleteById(id);
    }
}
