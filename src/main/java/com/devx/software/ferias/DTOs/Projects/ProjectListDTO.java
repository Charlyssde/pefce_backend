package com.devx.software.ferias.DTOs.Projects;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.DTOs.Enterprises.EnterpriseBasicDTO;

import java.util.Date;
import java.util.List;

public class ProjectListDTO {
    private Long id;
    private CatalogsDTO tipoId;
    private String folio;
    private EnterpriseBasicDTO empresaId;
    private String nombre;
    private String descripcion;
    private String prioridad;
    private Date fechaInicio;
    private Date fechaFin;
    private String area;
    private String estatus;
    private Boolean activo;
    private Date createdAt;
    private Date updatedAt;
    private List<ProjectColaboratorDTO> colaboradores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsDTO getTipoId() {
        return tipoId;
    }

    public void setTipoId(CatalogsDTO tipoId) {
        this.tipoId = tipoId;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EnterpriseBasicDTO getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(EnterpriseBasicDTO empresaId) {
        this.empresaId = empresaId;
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

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public List<ProjectColaboratorDTO> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<ProjectColaboratorDTO> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
