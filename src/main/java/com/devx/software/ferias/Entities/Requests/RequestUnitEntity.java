package com.devx.software.ferias.Entities.Requests;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_solicitudes_nave")
public class RequestUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "terreno")
    private Float terreno;

    @Column(name = "ubicacion_solicitada")
    private String ubicacionSolicitada;

    @Column(name = "agua", nullable = true)
    private Float agua;

    @Column(name = "densidad_poblacional", nullable = true)
    private Float densidadPoblacional;

    @Column(name = "caracteristicas")
    private String caracteristicas = "{\"gas\":false,\"electricidad\":false,\"vocacion\":false,\"drenaje\":false,\"carretera\":false,\"puerto\":false,\"via_ferrea\":false}";

    @Column(name = "otros_requerimientos", nullable = true)
    private String otrosRequerimientos;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTerreno() {
        return terreno;
    }

    public void setTerreno(Float terreno) {
        this.terreno = terreno;
    }

    public String getUbicacionSolicitada() {
        return ubicacionSolicitada;
    }

    public void setUbicacionSolicitada(String ubicacionSolicitada) {
        this.ubicacionSolicitada = ubicacionSolicitada;
    }

    public Float getAgua() {
        return agua;
    }

    public void setAgua(Float agua) {
        this.agua = agua;
    }

    public Float getDensidadPoblacional() {
        return densidadPoblacional;
    }

    public void setDensidadPoblacional(Float densidadPoblacional) {
        this.densidadPoblacional = densidadPoblacional;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getOtrosRequerimientos() {
        return otrosRequerimientos;
    }

    public void setOtrosRequerimientos(String otrosRequerimientos) {
        this.otrosRequerimientos = otrosRequerimientos;
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
}
