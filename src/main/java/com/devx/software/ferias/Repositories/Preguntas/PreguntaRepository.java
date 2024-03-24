/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Preguntas;

import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author blopez
 */
public interface PreguntaRepository extends JpaRepository<Preguntas, Long>{
    
}
