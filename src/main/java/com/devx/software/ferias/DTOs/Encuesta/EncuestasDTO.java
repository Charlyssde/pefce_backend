/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.DTOs.Encuesta;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author blopez
 */
public class EncuestasDTO {
    
      //ENCUESTA OBJETO
    private String nombre;
    private String descripcion;
   // private Date fechaCreacion;
    private UserEntity creadoPor;
    private List<EnterpriseEntity> empresas;

    // PREGUNTAS
   
    private String pregunta;
   // private Date fechaCreacionP;
    private EnterpriseEntity id_encuesta;
   // private UserEntity creadoPorp;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
//
//    public Date getFechaCreacion() {
//        return fechaCreacion;
//    }
//
//    public void setFechaCreacion(Date fechaCreacion) {
//        this.fechaCreacion = fechaCreacion;
//    }

    public UserEntity getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(UserEntity creadoPor) {
        this.creadoPor = creadoPor;
    }

    public List<EnterpriseEntity> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EnterpriseEntity> empresas) {
        this.empresas = empresas;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

//    public Date getFechaCreacionP() {
//        return fechaCreacionP;
//    }
//
//    public void setFechaCreacionP(Date fechaCreacionP) {
//        this.fechaCreacionP = fechaCreacionP;
//    }

    public EnterpriseEntity getId_encuesta() {
        return id_encuesta;
    }

    public void setId_encuesta(EnterpriseEntity id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

//    public UserEntity getCreadoPorp() {
//        return creadoPorp;
//    }
//
//    public void setCreadoPorp(UserEntity creadoPorp) {
//        this.creadoPorp = creadoPorp;
//    }
    
    
    
    
}
