/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Entities.Encuestas;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author blopez
 */
@Entity
@Table(name = "empresas_encuestas")
public class EmpresasEncuestasEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EnterpriseEntity empresa;

    @ManyToOne
    @JoinColumn(name = "id_encuesta")
    private Encuestas encuesta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnterpriseEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EnterpriseEntity empresa) {
        this.empresa = empresa;
    }

    public Encuestas getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuestas encuesta) {
        this.encuesta = encuesta;
    }


    
    
}
