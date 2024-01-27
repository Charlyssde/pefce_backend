package com.devx.software.ferias.DTOs.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.EstadosEntity;

public class EstadoDTO {

    private Long id;

    private String estado;

    private String claveEstado;

    // private String coords;

    public EstadoDTO() {// vacio
    }

    public EstadoDTO(EstadosEntity entity) {// a partir del entity
        this.id = entity.getId();
        this.estado = entity.getEstado();
        this.claveEstado = entity.getClaveEstado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClaveEstado() {
        return claveEstado;
    }

    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }

    // public String getCoords() { return coords; }

    // public void setCoords(String coords) { this.coords = coords;}
}
