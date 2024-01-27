package com.devx.software.ferias.DTOs.Encuestas;

import com.devx.software.ferias.DTOs.Files.FileDTO;
import java.util.Date;
import java.util.List;

public class EncuestaDTO {

    private Long id;
    private String titulo;
    private String descripcion;

    private Date createdAt;
    private Date updatedAt;
    private List<FileDTO> archivo;

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

    public List<FileDTO> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<FileDTO> archivo) {
        this.archivo = archivo;
    }
}
