package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaTareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinutaTareaRepository extends JpaRepository<MinutaTareaEntity, Long> {
    MinutaTareaEntity findMinutaTareaById(Long id);
}
