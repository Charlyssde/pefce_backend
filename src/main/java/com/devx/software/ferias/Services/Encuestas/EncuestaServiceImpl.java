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
import com.devx.software.ferias.Repositories.Users.UsersRepository;

/**
 *
 * @author blopez
 */
@Service
public class EncuestaServiceImpl implements EncuestaService{
    
    private final EncuestaRepository encuestaRepository;
    private final UsersRepository  usersRepository;
    private final EmpresasEncuestasEntityRepository   empresasEncuestasEntityRepository ;

    @Autowired
    public EncuestaServiceImpl(EncuestaRepository encuestaRepository, UsersRepository usersRepository, EmpresasEncuestasEntityRepository empresasEncuestasEntityRepository) {
        this.encuestaRepository = encuestaRepository;
        this.usersRepository = usersRepository;
        this.empresasEncuestasEntityRepository = empresasEncuestasEntityRepository;
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
    public Encuestas save(Encuestas encuestas) {

        Encuestas nueva = new Encuestas();
        nueva.setCreadoPor(encuestas.getCreadoPor());
        nueva.setDescripcion(encuestas.getDescripcion());
        nueva.setEmpresas(encuestas.getEmpresas());
        nueva.setFechaCreacion(new Date());
        nueva.setNombre(encuestas.getNombre());
        encuestaRepository.save(nueva);

//        List<EnterpriseEntity> listempresa = (List<EnterpriseEntity>) encuestas.getEmpresas();
//        for (EnterpriseEntity enterpriseEntity : listempresa) {
//            EmpresasEncuestasEntity nuevarelacion = new EmpresasEncuestasEntity();
//            nuevarelacion.setEncuesta(encuestas);
//            nuevarelacion.setEmpresa(enterpriseEntity);
//            empresasEncuestasEntityRepository.save(nuevarelacion);
//        }

        return null;
    }

    @Override
    public Encuestas update(Long id, Encuestas encuestas) {
        Encuestas toUpdate = this.encuestaRepository.findById(id).get();
         
        toUpdate.setCreadoPor(encuestas.getCreadoPor());
        toUpdate.setDescripcion(encuestas.getDescripcion());
        toUpdate.setEmpresas(encuestas.getEmpresas());
        toUpdate.setFechaCreacion(toUpdate.getFechaCreacion());
        toUpdate.setNombre(encuestas.getNombre());
        
        encuestaRepository.save(toUpdate);
        
        return null;
    }

    @Override
    public Encuestas deleted(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
