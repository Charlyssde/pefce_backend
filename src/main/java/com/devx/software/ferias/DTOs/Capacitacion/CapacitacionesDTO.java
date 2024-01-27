package com.devx.software.ferias.DTOs.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;

import java.util.Date;

public class CapacitacionesDTO {
    private Long id;

    private String nombre;

    private Date fechaInicio;

    private Date fechaFin;

    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    private String descripcion;

    private String imagenPerfil;

    private String portada;

    private String subarea;

    public CapacitacionesDTO() {// vacio
    }

    public CapacitacionesDTO(CapacitacionesEntity entity) {// a partir del entity
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaFin = entity.getFechaFin();
        this.activo = entity.getActivo();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.descripcion = entity.getDescripcion();
        this.imagenPerfil = entity.getImagenPerfil();
        this.portada = entity.getPortada();
        this.subarea = entity.getSubarea();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

}
