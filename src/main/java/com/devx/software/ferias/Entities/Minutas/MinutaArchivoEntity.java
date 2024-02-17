package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_minuta__a_archivo")
public class MinutaArchivoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_minuta")
    @JsonBackReference
    private MinutasEntity minuta;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsable")
    private UserEntity responsable;

    @Column
    private String archivo;

    @Column
    private String nombre;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;
    
    @Column
    private String url;
    
     @Column
    private String tipo;
     
     @Column
    private long tamaño;

    public MinutaArchivoEntity() {
    }
     
     
    public MinutaArchivoEntity(Long id, MinutasEntity minuta, UserEntity responsable, String archivo, String nombre, Date createdAt, Date updatedAt, String url, String tipo, long tamaño) {
        this.id = id;
        this.minuta = minuta;
        this.responsable = responsable;
        this.archivo = archivo;
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
        this.tipo = tipo;
        this.tamaño = tamaño;
    }
     
     

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getTamaño() {
        return tamaño;
    }

    public void setTamaño(long tamaño) {
        this.tamaño = tamaño;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MinutasEntity getMinuta() {
        return minuta;
    }

    public void setMinuta(MinutasEntity minuta) {
        this.minuta = minuta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public UserEntity getResponsable() {
        return responsable;
    }

    public void setResponsable(UserEntity responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
