package com.devx.software.ferias.Repositories.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesVideosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PabellonesVideosRepository extends JpaRepository<PabellonesVideosEntity, Long> {
    List<PabellonesVideosEntity> findAllByPabellonesId(Long id);
}
