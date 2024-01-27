package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.ModulosEntity;
import com.devx.software.ferias.Repositories.Capacitacion.ModulosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulosService {

    private final ModulosRepository modulosRepository;

    public ModulosService(ModulosRepository modulosRepository) {
        this.modulosRepository = modulosRepository;
    }

    public ModulosEntity create(ModulosEntity entity) throws Exception {
        try {
            return modulosRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ModulosEntity> page() {
        return modulosRepository.findAll();
    }

    public ModulosEntity findById(Long id) {
        return modulosRepository.findModuloById(id);
    }

    public List<ModulosEntity> findByCapacitacionId(Long id) {
        return modulosRepository.findByCapacitacionId(id);
    }

    public ModulosEntity update(ModulosEntity entity) {
        return modulosRepository.save(entity);
    }

    public void deleteById(Long id) {
        modulosRepository.deleteById(id);
    }
}
