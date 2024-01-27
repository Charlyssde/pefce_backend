package com.devx.software.ferias.DTOs.Auth;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;

public class EnterpriseRegistrationoRequest {
    private EnterpriseEntity empresa;

    public EnterpriseEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EnterpriseEntity empresa) {
        this.empresa = empresa;
    }
}
