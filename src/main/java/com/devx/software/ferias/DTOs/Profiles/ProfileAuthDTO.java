package com.devx.software.ferias.DTOs.Profiles;

import java.util.Date;
import java.util.List;

public class ProfileAuthDTO {
    private Long id;

    private String nombre;

    private String tipo;

    private Long nivel;

    private String area;

    private Boolean estatus;

    private Date createdAt;

    private Date updatedAt;

    private List<PermissionDTO> permisos;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
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

    public List<PermissionDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermissionDTO> permisos) {
        this.permisos = permisos;
    }
}
