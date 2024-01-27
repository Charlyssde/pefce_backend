package com.devx.software.ferias.Repositories.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.EstadosMunicipiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadosMunicipiosRepository extends JpaRepository<EstadosMunicipiosEntity, Long> {
    List<EstadosMunicipiosEntity> findAllByEstadoId_Id(Long id);
}
