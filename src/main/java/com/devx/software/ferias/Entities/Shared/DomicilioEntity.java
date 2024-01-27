package com.devx.software.ferias.Entities.Shared;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_domicilios")
public class DomicilioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle", nullable = true, columnDefinition = "varchar(250)")
    private String calle;

    @Column(name = "colonia", nullable = true, columnDefinition = "varchar(250)")
    private String colonia;

    @Column(name = "codigo_postal", nullable = true, columnDefinition = "varchar(10)")
    private String codigoPostal;

    @Column(name = "municipio", nullable = true, columnDefinition = "varchar(250)")
    private String municipio;

    @Column(name = "estado", nullable = true, columnDefinition = "varchar(250)")
    private String estado;

    @Column(name = "pais", nullable = true, columnDefinition = "varchar(250)")
    private String pais;

    @Column(name = "estatus", nullable = true, columnDefinition = "boolean")
    private Boolean estatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @JsonBackReference
    @ManyToMany(mappedBy = "domicilios")
    private List<UserEntity> users = new ArrayList<UserEntity>();

    public DomicilioEntity() {
    }


    public DomicilioEntity(String calle, String colonia, String codigoPostal, String municipio, String estado, String pais) {
        this.id = null;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
        this.estatus = true;
        this.createdAt = new Date();
        this.updatedAt = null;
    }

    public void addUser(UserEntity user) {
        this.users.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
