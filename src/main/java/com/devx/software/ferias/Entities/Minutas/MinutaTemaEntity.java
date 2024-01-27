package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "a_minuta__a_temas")
public class MinutaTemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_minuta")
    @JsonBackReference
    private MinutasEntity minuta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tema_minuta")
    private CatalogsEntity temaMinuta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MinutasEntity getMinuta() {
        return minuta;
    }

    public void setMinuta(MinutasEntity minuta) {
        this.minuta = minuta;
    }

    public CatalogsEntity getTemaMinuta() {
        return temaMinuta;
    }

    public void setTemaMinuta(CatalogsEntity temaMinuta) {
        this.temaMinuta = temaMinuta;
    }
}
