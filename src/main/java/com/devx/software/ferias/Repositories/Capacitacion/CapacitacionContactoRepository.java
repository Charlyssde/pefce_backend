package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapacitacionContactoRepository extends JpaRepository<CapacitacionContactoEntity, Long> {
    List<CapacitacionContactoEntity> findAllByCapacitacion_Id(Long idCapacitacion);
}
