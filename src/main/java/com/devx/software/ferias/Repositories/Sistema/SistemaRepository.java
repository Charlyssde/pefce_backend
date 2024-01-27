package com.devx.software.ferias.Repositories.Sistema;

import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SistemaRepository extends JpaRepository<SistemaEntity, Long> {
    List<SistemaEntity> findAll();
}
