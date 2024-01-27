package com.devx.software.ferias.Repositories.Autodiagnosticos;

import com.devx.software.ferias.Entities.Autodiagnosticos.AutodiagnosticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutodiagnosticosRepository extends JpaRepository<AutodiagnosticoEntity, Long> {

    //AutodiagnosticoEntity findByEmpresaId(Long empresaId);
    //AutodiagnosticoEntity findFirstByEmpresaId(Long empresaId);
}
