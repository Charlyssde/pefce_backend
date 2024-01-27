package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Shared.AddressDTO;

import java.util.Date;
import java.util.List;

public class EnterpriseList_ContactDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Boolean recibirPublicidad;
    private Boolean estatus;
    private Date createdAt;
    private Date updatedAt;
    private List<AddressDTO> domicilios;

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

    public List<AddressDTO> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<AddressDTO> domicilios) {
        this.domicilios = domicilios;
    }
}
