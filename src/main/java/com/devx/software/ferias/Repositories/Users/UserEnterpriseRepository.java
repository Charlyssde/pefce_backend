/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Users;

import com.devx.software.ferias.Entities.UsuarioEmpresa.UsuarioEmpresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blopez
 */
@Repository
public interface UserEnterpriseRepository extends JpaRepository<UsuarioEmpresa, Long>{
    

    @Query(
        value = "SELECT * FROM a_usuario_empresa__ aue " +
                "INNER JOIN a_evento__a_participantes aep " +
                "ON aep.usuario_id = aue.id_usuario " +
                "WHERE aep.evento_id IN (:eventoIds)",
        nativeQuery = true
    )
    List<UsuarioEmpresa> findUsuariosByEventoIds(@Param("eventoIds") List<Long> eventoIds);
}
