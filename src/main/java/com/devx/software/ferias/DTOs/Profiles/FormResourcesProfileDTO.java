package com.devx.software.ferias.DTOs.Profiles;

import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;

import java.util.Date;
import java.util.List;

public class FormResourcesProfileDTO {

    private Long id;

    private String nombre;

    private String tipo;

    private String area;

    private Long perfilId;

    private Long nivel;

    private boolean estatus;

    private Date createdAt;

    private Date updatedAt;

    private List<ProfileMenuEntity> permisos;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
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

    public List<ProfileMenuEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<ProfileMenuEntity> permisos) {
        this.permisos = permisos;
    }
}
