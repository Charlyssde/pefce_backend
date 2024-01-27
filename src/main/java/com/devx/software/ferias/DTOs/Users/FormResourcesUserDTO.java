package com.devx.software.ferias.DTOs.Users;

import com.devx.software.ferias.DTOs.Profiles.ProfileDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormResourcesUserDTO {

    private Long id;

    private String nombre;

    private String email;

    private String telefono = null;

    private String sexo;

    private Boolean recibirPublicidad;

    private Boolean estatus;

    private Date createdAt;

    private Date updatedAt;

    private List<ProfileDTO> perfiles = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getRecibirPublicidad() {
        return recibirPublicidad;
    }

    public void setRecibirPublicidad(Boolean recibirPublicidad) {
        this.recibirPublicidad = recibirPublicidad;
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

    public List<ProfileDTO> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<ProfileDTO> perfiles) {
        this.perfiles = perfiles;
    }
}
