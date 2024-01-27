package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Shared.AddressDTO;

import java.util.Date;
import java.util.List;

public class FormResourceEnterpriseDTO {

    private Long id;
    private String empresa;
    private String rfc;
    private EnterpriseList_CatalogDTO regimenFiscal;
    private EnterpriseList_CatalogDTO categoria;
    private EnterpriseList_CatalogDTO sector;
    private EnterpriseList_CatalogDTO subsector;
    private String telefono;
    private String email;
    private String website;
    private String descripcion;
    private String representanteLegal;
    private Long numeroColaboradoresMasculinos;
    private Long numeroColaboradoresFemeninos;
    private Long numeroColaboradoresCapacidadesDiferentes;
    private Long totalColaboradores;
    private Boolean pabellonAprobado;
    private Boolean estatus;
    private Date createdAt;
    private Date updatedAt;
    private List<AddressDTO> domicilios;
    private List<EnterpriseList_ContactDTO> contactos;
    private List<EnterpriseAccessRequestDTO> solicitudesAcceso;
    private List<EnterpriseTradeImageDTO> imagenEmpresarial;
    private List<ProductDTO> productos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public EnterpriseList_CatalogDTO getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(EnterpriseList_CatalogDTO regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public EnterpriseList_CatalogDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(EnterpriseList_CatalogDTO categoria) {
        this.categoria = categoria;
    }

    public EnterpriseList_CatalogDTO getSector() {
        return sector;
    }

    public void setSector(EnterpriseList_CatalogDTO sector) {
        this.sector = sector;
    }

    public EnterpriseList_CatalogDTO getSubsector() {
        return subsector;
    }

    public void setSubsector(EnterpriseList_CatalogDTO subsector) {
        this.subsector = subsector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public Long getNumeroColaboradoresMasculinos() {
        return numeroColaboradoresMasculinos;
    }

    public void setNumeroColaboradoresMasculinos(Long numeroColaboradoresMasculinos) {
        this.numeroColaboradoresMasculinos = numeroColaboradoresMasculinos;
    }

    public Long getNumeroColaboradoresFemeninos() {
        return numeroColaboradoresFemeninos;
    }

    public void setNumeroColaboradoresFemeninos(Long numeroColaboradoresFemeninos) {
        this.numeroColaboradoresFemeninos = numeroColaboradoresFemeninos;
    }

    public Long getNumeroColaboradoresCapacidadesDiferentes() {
        return numeroColaboradoresCapacidadesDiferentes;
    }

    public void setNumeroColaboradoresCapacidadesDiferentes(Long numeroColaboradoresCapacidadesDiferentes) {
        this.numeroColaboradoresCapacidadesDiferentes = numeroColaboradoresCapacidadesDiferentes;
    }

    public Long getTotalColaboradores() {
        return totalColaboradores;
    }

    public void setTotalColaboradores(Long totalColaboradores) {
        this.totalColaboradores = totalColaboradores;
    }

    public Boolean getPabellonAprobado() {
        return pabellonAprobado;
    }

    public void setPabellonAprobado(Boolean pabellonAprobado) {
        this.pabellonAprobado = pabellonAprobado;
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

    public List<AddressDTO> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<AddressDTO> domicilios) {
        this.domicilios = domicilios;
    }

    public List<EnterpriseList_ContactDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<EnterpriseList_ContactDTO> contactos) {
        this.contactos = contactos;
    }

    public List<EnterpriseAccessRequestDTO> getSolicitudesAcceso() {
        return solicitudesAcceso;
    }

    public void setSolicitudesAcceso(List<EnterpriseAccessRequestDTO> solicitudesAcceso) {
        this.solicitudesAcceso = solicitudesAcceso;
    }

    public List<EnterpriseTradeImageDTO> getImagenEmpresarial() {
        return imagenEmpresarial;
    }

    public void setImagenEmpresarial(List<EnterpriseTradeImageDTO> imagenEmpresarial) {
        this.imagenEmpresarial = imagenEmpresarial;
    }

    public List<ProductDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductDTO> productos) {
        this.productos = productos;
    }
}
