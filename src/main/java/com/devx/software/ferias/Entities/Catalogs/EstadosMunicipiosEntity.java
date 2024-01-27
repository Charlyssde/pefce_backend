package com.devx.software.ferias.Entities.Catalogs;

import javax.persistence.*;

@Entity
@Table(name = "a_estados_municipios")
public class EstadosMunicipiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "estados_id")
    private EstadosEntity estadoId;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "municipios_id")
    private MunicipiosEntity municipioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadosEntity getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadosEntity estadoId) {
        this.estadoId = estadoId;
    }

    public MunicipiosEntity getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(MunicipiosEntity municipioId) {
        this.municipioId = municipioId;
    }
}
