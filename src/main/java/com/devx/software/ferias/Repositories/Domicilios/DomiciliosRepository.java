package com.devx.software.ferias.Repositories.Domicilios;

import com.devx.software.ferias.Entities.Shared.DomicilioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomiciliosRepository extends JpaRepository<DomicilioEntity, Long> {
}
