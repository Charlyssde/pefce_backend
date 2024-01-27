package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.UsuarioMinutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMinutaRepository extends JpaRepository<UsuarioMinutaEntity, Long> {
    UsuarioMinutaEntity findUsuarioMinutaById(Long id);
}
