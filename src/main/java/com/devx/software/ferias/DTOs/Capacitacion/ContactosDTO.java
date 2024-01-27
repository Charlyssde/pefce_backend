package com.devx.software.ferias.DTOs.Capacitacion;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import java.util.Date;

public class ContactosDTO {
    private Long id;

    private CatalogsEntity idioma;

    private CatalogsEntity fuente;

    private String nombre;

    private String sexo;

    private String titulo;

    private String puesto;

    private String departamento;

    private String correo;

    private String correoAlternativo;

    private Boolean recibirCorreos;

    private Long telefono;

    private Long extension;

    private Long telefonoAlterno;

    private String direccion;

    private String estatus;

    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsEntity getIdioma() {
        return idioma;
    }

    public void setIdioma(CatalogsEntity idioma) {
        this.idioma = idioma;
    }

    public CatalogsEntity getFuente() {
        return fuente;
    }

    public void setFuente(CatalogsEntity fuente) {
        this.fuente = fuente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }

    public Boolean getRecibirCorreos() {
        return recibirCorreos;
    }

    public void setRecibirCorreos(Boolean recibirCorreos) {
        this.recibirCorreos = recibirCorreos;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getExtension() {
        return extension;
    }

    public void setExtension(Long extension) {
        this.extension = extension;
    }

    public Long getTelefonoAlterno() {
        return telefonoAlterno;
    }

    public void setTelefonoAlterno(Long telefonoAlterno) {
        this.telefonoAlterno = telefonoAlterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
}
