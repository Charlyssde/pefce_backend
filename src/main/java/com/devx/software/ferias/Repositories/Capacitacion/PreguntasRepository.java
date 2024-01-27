package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntasRepository extends JpaRepository<PreguntasEntity, Long> {
    PreguntasEntity findPreguntaById(Long id);

    List<PreguntasEntity> findByTemaId(Long id);
}
