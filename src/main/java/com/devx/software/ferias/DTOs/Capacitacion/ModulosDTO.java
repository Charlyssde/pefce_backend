package com.devx.software.ferias.DTOs.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionesDTO;

import java.util.Date;

public class ModulosDTO {

    private Long id;

    private CapacitacionesDTO capacitacion;

    private String nombre;

    private String descripcion;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CapacitacionesDTO getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionesDTO capacitacion) {
        this.capacitacion = capacitacion;
    }

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

}
