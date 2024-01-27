package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaTemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinutaTemaRepository extends JpaRepository<MinutaTemaEntity, Long> {
    MinutaTemaEntity findMinutaTemaById(Long id);
}
