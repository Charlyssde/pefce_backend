package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import com.devx.software.ferias.Repositories.Capacitacion.PreguntasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntasService {
    private final PreguntasRepository preguntasRespository;

    public PreguntasService(PreguntasRepository preguntasRepository) {
        this.preguntasRespository = preguntasRepository;
    }

    public PreguntasEntity create(PreguntasEntity entity) throws Exception {
        try {
            return preguntasRespository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<PreguntasEntity> page() {
        return preguntasRespository.findAll();
    }

    public PreguntasEntity findById(Long id) {
        return preguntasRespository.findPreguntaById(id);
    }

    public List<PreguntasEntity> findByTemaId(Long id) {
        return preguntasRespository.findByTemaId(id);
    }

    public PreguntasEntity update(PreguntasEntity entity) {
        return preguntasRespository.save(entity);
    }

    public void deleteById(Long id) {
        preguntasRespository.deleteById(id);
    }
}
