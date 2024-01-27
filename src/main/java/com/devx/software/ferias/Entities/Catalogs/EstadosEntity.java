package com.devx.software.ferias.Entities.Catalogs;

import javax.persistence.*;

@Entity
@Table(name = "a_estados")
public class EstadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String estado;

    @Column
    private String claveEstado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClaveEstado() {
        return claveEstado;
    }

    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }

    //public String getCoords() { return coords; }

    //public void setCoords(String coords) { this.coords = coords; }
}
