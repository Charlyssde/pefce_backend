package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.UsuarioCapacitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioCapacitacionRepository extends JpaRepository<UsuarioCapacitacionEntity, Long> {

    UsuarioCapacitacionEntity findByCapacitacionIdAndUsuarioId(Long idCapacitacion, Long idUsuario);

    UsuarioCapacitacionEntity findByUuidFinalizado(String uuid);

    List<UsuarioCapacitacionEntity> findByCapacitacionId(Long id);

    List<UsuarioCapacitacionEntity> findByUsuarioId(Long idUsuario);
}
