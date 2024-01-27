package com.devx.software.ferias.DTOs.Catalogs;

public class EstadoMunicipioDTO {

    private Long id;

    private EstadoDTO estadoId;

    private MunicipioDTO municipioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoDTO getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadoDTO estadoId) {
        this.estadoId = estadoId;
    }

    public MunicipioDTO getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(MunicipioDTO municipioId) {
        this.municipioId = municipioId;
    }
}
