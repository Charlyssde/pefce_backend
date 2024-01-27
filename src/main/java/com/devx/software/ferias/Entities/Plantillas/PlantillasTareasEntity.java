package com.devx.software.ferias.Entities.Plantillas;

import javax.persistence.*;

@Entity
@Table(name = "a_plantillas__tareas")
public class PlantillasTareasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = true)
    private String entregable;

    @Column(nullable = false)
    private String dias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}




