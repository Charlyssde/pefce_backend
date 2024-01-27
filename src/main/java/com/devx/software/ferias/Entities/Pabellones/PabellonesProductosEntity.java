package com.devx.software.ferias.Entities.Pabellones;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_pabellones__a_productos")
public class PabellonesProductosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PabellonesEntity pabellones;

    @Column
    private String pathfileDocumento;

    @Column
    private String pathfileImagen;

    @Column
    private String producto;

    @Column
    private Boolean estatus;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PabellonesEntity getPabellones() {
        return pabellones;
    }

    public void setPabellones(PabellonesEntity pabellones) {
        this.pabellones = pabellones;
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
