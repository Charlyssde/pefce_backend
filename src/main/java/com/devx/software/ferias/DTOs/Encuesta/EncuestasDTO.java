/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.DTOs.Encuesta;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author blopez
 */
public class EncuestasDTO implements Serializable{
   
    private Long Id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private UserEntity creadoPor;
    private List<EventEntity> eventos;
    private Set<EnterpriseEntity> empresas;

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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public UserEntity getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(UserEntity creadoPor) {
        this.creadoPor = creadoPor;
    }

    public List<EventEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventEntity> eventos) {
        this.eventos = eventos;
    }

    public Set<EnterpriseEntity> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<EnterpriseEntity> empresas) {
        this.empresas = empresas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }


    
    
}
