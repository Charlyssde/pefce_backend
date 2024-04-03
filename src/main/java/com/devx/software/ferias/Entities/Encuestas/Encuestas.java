/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Entities.Encuestas;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author blopez
 */

@Entity
@Table(name = " encuestas")
public class Encuestas implements Serializable  {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
     @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private UserEntity creadoPor;

    @ManyToMany
    @JoinTable(
        name = "empresas_encuestas",
        joinColumns = @JoinColumn(name = "id_encuesta"),
        inverseJoinColumns = @JoinColumn(name = "id_empresa")
    )
    private Set<EnterpriseEntity> empresas;

    public Encuestas() {
    }

    public Encuestas(Long id, String nombre, String descripcion, Date fechaCreacion, UserEntity creadoPor, Set<EnterpriseEntity> empresas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
        this.empresas = empresas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public UserEntity getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(UserEntity creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Set<EnterpriseEntity> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<EnterpriseEntity> empresas) {
        this.empresas = empresas;
    }
    
    
}
