package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Files.FileDTO;

import java.util.Date;
import java.util.List;

public class ProductDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String empaqueEnvasado;
    private String embalaje;
    private String estibado;
    private String condicionesAlmacenamiento;
    private String transporte;
    private String vidaAnaquel;
    private String lugarOrigen;
    private Boolean estatus;
    private Date createdAt;
    private Date updatedAt;
    private List<FileDTO> fichaTecnica;
    private List<FileDTO> imagenes;
    private List<FileDTO> videos;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpaqueEnvasado() {
        return empaqueEnvasado;
    }

    public void setEmpaqueEnvasado(String empaqueEnvasado) {
        this.empaqueEnvasado = empaqueEnvasado;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getEstibado() {
        return estibado;
    }

    public void setEstibado(String estibado) {
        this.estibado = estibado;
    }

    public String getCondicionesAlmacenamiento() {
        return condicionesAlmacenamiento;
    }

    public void setCondicionesAlmacenamiento(String condicionesAlmacenamiento) {
        this.condicionesAlmacenamiento = condicionesAlmacenamiento;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getVidaAnaquel() {
        return vidaAnaquel;
    }

    public void setVidaAnaquel(String vidaAnaquel) {
        this.vidaAnaquel = vidaAnaquel;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
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

    public List<FileDTO> getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(List<FileDTO> fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public List<FileDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<FileDTO> imagenes) {
        this.imagenes = imagenes;
    }

    public List<FileDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<FileDTO> videos) {
        this.videos = videos;
    }
}
