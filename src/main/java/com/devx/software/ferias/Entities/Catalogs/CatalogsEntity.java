package com.devx.software.ferias.Entities.Catalogs;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_catalogos")
public class CatalogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipoCatalogo;

    @Column
    private String nombre;

    @Column
    private Boolean activo;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private Long idCatalogoPadre;

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
