package com.devx.software.ferias.Services.Plantillas;

import com.devx.software.ferias.Entities.Plantillas.PlantillasEntity;
import com.devx.software.ferias.Repositories.Plantillas.PlantillasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantillasService {

    private final PlantillasRepository plantillaRepository;

    @Autowired
    public PlantillasService(
            PlantillasRepository plantillaRepository
    ) {
        this.plantillaRepository = plantillaRepository;
    }


    //    READ
    public List<PlantillasEntity> getAll() {
        return this.plantillaRepository.findAll();
    }

    public List<PlantillasEntity> getAllByUsuario(Long usuarioId) {
        return this.plantillaRepository.findAllByUsuarioId(usuarioId);
    }

    public List<PlantillasEntity> getAllByUsuarioId(Long usuarioId) {
        return this.plantillaRepository.findAllByUsuarioIdId(usuarioId);
    }

    public Optional<PlantillasEntity> findById(Long id) {
        return this.plantillaRepository.findById(id);
    }

    public PlantillasEntity findPlantillasById(Long id) {
        return this.plantillaRepository.findPlantillasById(id);
    }

    public PlantillasEntity save(PlantillasEntity model) {
        return this.save(model);
    }

    public PlantillasEntity saveT(PlantillasEntity model) {
        this.plantillaRepository.save(model);
        return model;
    }

    public PlantillasEntity update(PlantillasEntity entity) {
        this.plantillaRepository.save(entity);

        return entity;
    }

    public Boolean delete(Long id) {
        try {
            Optional<PlantillasEntity> model = this.findById(id);
            if (model.isPresent()) {
                this.plantillaRepository.delete(model.get());
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
