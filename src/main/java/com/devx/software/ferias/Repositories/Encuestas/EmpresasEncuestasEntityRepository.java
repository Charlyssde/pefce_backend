/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Encuestas;

import com.devx.software.ferias.Entities.Encuestas.EmpresasEncuestasEntity;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author blopez
 */
public interface EmpresasEncuestasEntityRepository extends JpaRepository<EmpresasEncuestasEntity, Long>  {

    List<EmpresasEncuestasEntity> findAllByEmpresa(EnterpriseEntity empresa);
    
}
