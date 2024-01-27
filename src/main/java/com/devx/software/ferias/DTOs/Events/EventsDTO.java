package com.devx.software.ferias.DTOs.Events;

import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;

import java.util.Date;
import java.util.List;

public class EventsDTO {
    private Long id;

    private String nombreEvento;

    private Date fechaInicio;

    private Date fechaFin;

    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    private String descripcion;

    private List<FileEntity> archivo;

    private String portada;

    private String subarea;

    public EventsDTO() {// vacio
    }

    public EventsDTO(EventEntity entity) {// a partir del entity
        this.id = entity.getId();
        this.nombreEvento = entity.getNombreEvento();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaFin = entity.getFechaFin();
        this.activo = entity.getActivo();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.descripcion = entity.getDescripcion();
        this.archivo = entity.getArchivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public List<FileEntity> getarchivo() {
        return archivo;
    }

    public void setArchivo( List<FileEntity>archivo) {
        this.archivo = archivo;
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
