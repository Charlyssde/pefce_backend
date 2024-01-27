package com.devx.software.ferias.DTOs.Enterprises;

import java.util.List;

public class EnterpriseListUserFilterDTO {
    private Long id;
    private String empresa;
    private String rfc;
    private String email;
    private boolean estatus;
    private List<EnterpriseList_ContactDTO> contactos;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public List<EnterpriseList_ContactDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<EnterpriseList_ContactDTO> contactos) {
        this.contactos = contactos;
    }
}
