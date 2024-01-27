package com.devx.software.ferias.DTOs.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;

public class MunicipioDTO {

    private Long id;

    private String municipio;

    public MunicipioDTO() {// vacio
    }

    public MunicipioDTO(MunicipiosEntity entity) {// a partir del entity
        this.id = getId();
        this.municipio = getMunicipio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
