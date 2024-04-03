/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Preguntas;

import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author blopez
 */
public interface PreguntaRepository extends JpaRepository<Preguntas, Long>{
    
    @Query(
            value = "select * from preguntas p where p.id_encuesta = ?1", nativeQuery = true)
    List<Preguntas> findAllbyencuesta(Long id);
    
}
