package com.devx.software.ferias.DTOs.Pabellones;

import java.util.Date;

public class PabellonesProductosDTO {
    private Long id;

    private Long pabellonesId;

    private String pathfileDocumento;

    private String pathfileImagen;

    private String producto;

    private Boolean estatus;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPabellonesId() {
        return pabellonesId;
    }

    public void setPabellonesId(Long pabellonesId) {
        this.pabellonesId = pabellonesId;
    }

    public String getPathfileDocumento() {
        return pathfileDocumento;
    }

    public void setPathfileDocumento(String pathfileDocumento) {
        this.pathfileDocumento = pathfileDocumento;
    }

    public String getPathfileImagen() {
        return pathfileImagen;
    }

    public void setPathfileImagen(String pathfileImagen) {
        this.pathfileImagen = pathfileImagen;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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


}
