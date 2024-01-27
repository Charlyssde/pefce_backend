package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.TemasEntity;
import com.devx.software.ferias.Repositories.Capacitacion.TemasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemasService {
    private final TemasRepository temasRepository;

    public TemasService(TemasRepository temasRepository) {
        this.temasRepository = temasRepository;
    }

    public TemasEntity create(TemasEntity entity) throws Exception {
        try {
            return temasRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<TemasEntity> page() {
        return temasRepository.findAll();
    }

    public TemasEntity findById(Long id) {
        return temasRepository.findTemaById(id);
    }

    public List<TemasEntity> findByModuloId(Long id) {
        return temasRepository.findByModuloId(id);
    }

    public TemasEntity update(TemasEntity entity) {
        return temasRepository.save(entity);
    }

    public void deleteById(Long id) {
        temasRepository.deleteById(id);
    }
}
