package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaArchivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinutaArchivoRepository extends JpaRepository<MinutaArchivoEntity, Long> {
    MinutaArchivoEntity findMinutaArchivoById(Long id);
}
