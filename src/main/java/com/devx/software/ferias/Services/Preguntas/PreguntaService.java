/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Services.Preguntas;

import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import java.util.List;

/**
 *
 * @author blopez
 */
public interface PreguntaService {
    List<Preguntas> findAll();
    
    Preguntas findById(Long id);
    
    Preguntas save(Preguntas preguntas);

    Preguntas update(Long id, Preguntas preguntas);
    
    Preguntas deleted (Long id);
}
