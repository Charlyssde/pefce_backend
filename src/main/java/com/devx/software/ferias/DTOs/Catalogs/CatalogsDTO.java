package com.devx.software.ferias.DTOs.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import java.util.Date;

public class CatalogsDTO {
    private Long id;
    private String tipoCatalogo;
    private String nombre;
    private Boolean activo;
    private Date createdAt;
    private Date updatedAt;
    private Long idCatalogoPadre;

    public CatalogsDTO() {// vacio
    }

    public CatalogsDTO(CatalogsEntity entity) {// a partir del entity
        this.id = entity.getId();
        this.tipoCatalogo = entity.getTipoCatalogo();
        this.nombre = entity.getNombre();
        this.activo = entity.getActivo();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.idCatalogoPadre = entity.getIdCatalogoPadre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCatalogo() {
        return tipoCatalogo;
    }

    public void setTipoCatalogo(String tipoCatalogo) {
        this.tipoCatalogo = tipoCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Long getIdCatalogoPadre() {
        return idCatalogoPadre;
    }

    public void setIdCatalogoPadre(Long idCatalogoPadre) {
        this.idCatalogoPadre = idCatalogoPadre;
    }
}
