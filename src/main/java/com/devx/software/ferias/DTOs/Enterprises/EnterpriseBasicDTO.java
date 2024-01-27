package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Shared.AddressDTO;

import java.util.Date;
import java.util.List;

public class EnterpriseBasicDTO {
    private Long id;
    private String empresa;
    private String rfc;

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
}
