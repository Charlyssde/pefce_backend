/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Services.Encuestas;

import com.devx.software.ferias.DTOs.Encuesta.EncuestasDTO;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import java.util.List;

/**
 *
 * @author blopez
 */
public interface EncuestaService {
     List<Encuestas> findAll();
    
    Encuestas findById(Long id);
    
    Encuestas save(Encuestas encuestas);

    Encuestas update(Long id, Encuestas encuestas);
    
    Encuestas deleted (Long id);
}
