/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Encuestas;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blopez
 */
@Repository
public interface EncuestaRepository extends JpaRepository<Encuestas, Long> {
    
    @Query(value = "select * from encuestas e\n"
            + "inner join eventos_encuestas ec on e.id = ec.id_encuesta\n"
            + "where ec.id_evento = :id", nativeQuery = true

    )
    List<Encuestas> findByEvento(Long id);
}
