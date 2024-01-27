package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.UsuarioPreguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPreguntaRepository extends JpaRepository<UsuarioPreguntaEntity, Long> {
    UsuarioPreguntaEntity findByPreguntaIdAndUsuarioId(Long idPregunta, Long idUsuario);
}
