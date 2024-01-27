package com.devx.software.ferias.Repositories.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PabellonesProductosRepository extends JpaRepository<PabellonesProductosEntity, Long> {
    List<PabellonesProductosEntity> findAllByPabellonesId(Long id);
}
