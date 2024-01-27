package com.devx.software.ferias.Repositories.Logs;

import com.devx.software.ferias.Entities.Logs.SessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<SessionEntity, Long> {

    @Query(
            value = "SELECT a_sesiones.* " +
                    "FROM a_sesiones " +
                    "WHERE a_sesiones.usuario_id = :userId " +
                    "AND a_sesiones.perfil_id IS NOT NULL " +
                    "ORDER BY a_sesiones.id DESC",
            nativeQuery = true
    )
    Page<SessionEntity> findAllByUsuarioOrderByIdDesc(Pageable pageable, Long userId);

    @Query(
            value = "SELECT a_sesiones.* " +
                    "FROM a_sesiones " +
                    "INNER JOIN a_usuarios on a_sesiones.usuario_id = a_usuarios.id " +
                    "INNER JOIN a_perfiles on a_sesiones.perfil_id = a_perfiles.id " +
                    "WHERE (" +
                    "(CAST(:nombre as text) is null OR CAST(a_usuarios.nombre as TEXT) ilike CONCAT('%',CAST(:nombre as TEXT),'%')) OR " +
                    "(CAST(:nombre as text) is null OR CAST(a_usuarios.email as TEXT) ilike CONCAT('%',CAST(:nombre as TEXT),'%')) OR " +
                    "(CAST(:nombre as text) is null OR CAST(a_perfiles.nombre as TEXT) ilike CONCAT('%',CAST(:nombre as TEXT),'%'))" +
                    ") " +
                    "AND a_sesiones.perfil_id IS NOT NULL " +
                    "ORDER BY a_sesiones.id DESC",
            nativeQuery = true
    )
    Page<SessionEntity> findAllByOrderByIdDesc(Pageable pageable, String nombre);

    List<SessionEntity> findAllByEstatusTrueAndSesionFinNotNullAndUsuario_Id(Long id);

    SessionEntity findSesionEntityByEstatusTrueAndUsuario_IdOrderByIdDesc(Long userId);
}
