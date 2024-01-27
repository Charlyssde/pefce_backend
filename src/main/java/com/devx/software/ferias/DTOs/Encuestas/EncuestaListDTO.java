package com.devx.software.ferias.DTOs.Encuestas;

import com.devx.software.ferias.DTOs.Files.FileDTO;
import com.devx.software.ferias.Entities.Files.FileEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EncuestaListDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_encuesta__a_archivo",
            joinColumns = @JoinColumn(name = "encuesta_id"),
            inverseJoinColumns = @JoinColumn(name = "archivo_id")
    )
    private List<FileEntity> archivo = new ArrayList<>();

    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<FileEntity> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<FileEntity> archivo) {
        this.archivo = archivo;
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
