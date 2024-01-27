package com.devx.software.ferias.Entities.Proyectos;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_proyectos")
public class ProyectosEntity {
    @Column(nullable = true)
    public String observaciones;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String folio;

    @Column(nullable = true)
    private String nombreProyecto;
    @Column(nullable = true)
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "prioridad", nullable = true)
    private CatalogsEntity prioridad;
    @Column(nullable = true)
    private Date fechaInicio;
    @Column(nullable = true)
    private Date fechaFin;
    @OneToOne
    @JoinColumn(name = "area", nullable = true)
    private CatalogsEntity area;
    @Column(nullable = true)
    private Float montoPrevisto;
    @Column(nullable = true)
    private Long empleosDirectos;
    @Column(nullable = true)
    private Long empleosIndirectos;
    @Column(nullable = true)
    private String oficioTurno;
    @OneToOne
    @JoinColumn(name = "estatus", nullable = true)
    private CatalogsEntity estatus;

    @Column
    private Boolean activo;

    @Column(nullable = true)
    private Date createdAt;

    @Column(nullable = true)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_proyecto__a_proyecto_historico",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_historico_id"))
    private List<ProyectosHistoricoEntity> historico = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "a_proyecto__a_tarea",
            joinColumns = {@JoinColumn(name = "proyecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "tarea_id")})
    private List<TaskEntity> tareas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "a_proyecto__a_proyecto_colaborador",
            joinColumns = {@JoinColumn(name = "proyecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "colaborador_id")})
    private List<ProyectosColaboradorEntity> colaboradores = new ArrayList<>();

    public void agregarTarea(TaskEntity tarea) {
        this.tareas.add(tarea);
    }

    public void agregarColaborador(ProyectosColaboradorEntity proyectoColaborador) {
        this.colaboradores.add(proyectoColaborador);
    }

    public void agregarHistorico(ProyectosHistoricoEntity hito) {
        this.historico.add(hito);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CatalogsEntity getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(CatalogsEntity prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public CatalogsEntity getArea() {
        return area;
    }

    public void setArea(CatalogsEntity area) {
        this.area = area;
    }

    public Float getMontoPrevisto() {
        return montoPrevisto;
    }

    public void setMontoPrevisto(Float montoPrevisto) {
        this.montoPrevisto = montoPrevisto;
    }

    public Long getEmpleosDirectos() {
        return empleosDirectos;
    }

    public void setEmpleosDirectos(Long empleosDirectos) {
        this.empleosDirectos = empleosDirectos;
    }

    public Long getEmpleosIndirectos() {
        return empleosIndirectos;
    }

    public void setEmpleosIndirectos(Long empleosIndirectos) {
        this.empleosIndirectos = empleosIndirectos;
    }

    public String getOficioTurno() {
        return oficioTurno;
    }

    public void setOficioTurno(String oficioTurno) {
        this.oficioTurno = oficioTurno;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CatalogsEntity getEstatus() {
        return estatus;
    }

    public void setEstatus(CatalogsEntity estatus) {
        this.estatus = estatus;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public List<ProyectosHistoricoEntity> getHistorico() {
        return historico;
    }

    public void setHistorico(List<ProyectosHistoricoEntity> historico) {
        this.historico = historico;
    }

    public List<TaskEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<TaskEntity> tareas) {
        this.tareas = tareas;
    }

    public List<ProyectosColaboradorEntity> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<ProyectosColaboradorEntity> colaboradores) {
        this.colaboradores = colaboradores;
    }

}
