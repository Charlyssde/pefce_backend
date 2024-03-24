/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Entities.Preguntas;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "preguntas")
public class Preguntas implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "pregunta")
    private String pregunta;
    
    
     @Column(name = "fecha_creacion")
    private Date fechaCreacion;
     
     @ManyToOne
    @JoinColumn(name = "id_encuesta")
    private Encuestas encuesta;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private UserEntity creadoPor;

    public Preguntas() {
    }

    public Preguntas(Long id, String pregunta, Date fechaCreacion, Encuestas encuesta, UserEntity creadoPor) {
        this.id = id;
        this.pregunta = pregunta;
        this.fechaCreacion = fechaCreacion;
        this.encuesta = encuesta;
        this.creadoPor = creadoPor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Encuestas getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuestas encuesta) {
        this.encuesta = encuesta;
    }

    public UserEntity getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(UserEntity creadoPor) {
        this.creadoPor = creadoPor;
    }


}
