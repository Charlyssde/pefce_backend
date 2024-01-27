package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.ModulosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulosRepository extends JpaRepository<ModulosEntity, Long> {

    ModulosEntity findModuloById(Long id);

    List<ModulosEntity> findByCapacitacionId(Long id);
}
