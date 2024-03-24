/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Services.Encuestas;

import com.devx.software.ferias.DTOs.Encuesta.EncuestasDTO;
import com.devx.software.ferias.Entities.Encuestas.EmpresasEncuestasEntity;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import com.devx.software.ferias.Repositories.Encuestas.EmpresasEncuestasEntityRepository;
import com.devx.software.ferias.Repositories.Encuestas.EncuestaRepository;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devx.software.ferias.Repositories.Preguntas.PreguntaRepository;

/**
 *
 * @author blopez
 */
@Service
public class EncuestaServiceImpl implements EncuestaService{
    
    private final EncuestaRepository encuestaRepository;

    @Autowired
     public EncuestaServiceImpl(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }
    

    @Override
    public List<Encuestas> findAll() {
        return  encuestaRepository.findAll();
    }

 
    @Override
    public Encuestas findById(Long id) {
      return encuestaRepository.findById(id).get();
    }

    @Override
    public Encuestas save(Encuestas Encuestas) {
   
        return encuestaRepository.save(Encuestas);
    }

    @Override
    public Encuestas update(Long id, Encuestas encuestas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Encuestas deleted(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
