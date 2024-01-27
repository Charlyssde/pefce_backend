package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.TemasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemasRepository extends JpaRepository<TemasEntity, Long> {

    TemasEntity findTemaById(Long id);

    List<TemasEntity> findByModuloId(Long id);
}
