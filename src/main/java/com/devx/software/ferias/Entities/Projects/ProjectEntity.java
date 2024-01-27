package com.devx.software.ferias.Entities.Projects;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_proyectos")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tipo_id", nullable = true)
    private CatalogsEntity tipoId;

    @Column(name = "folio", nullable= true)
    private String folio;

    @OneToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    private EnterpriseEntity empresaId;

    @Column(name = "nombre",nullable = true)
    private String nombre;

    @Column(name = "descripcion",nullable = true)
    private String descripcion;

    @Column(name = "prioridad", nullable = true)
    private String prioridad;

    @Column(nullable = false, columnDefinition = "timestamp")
    private Date fechaInicio;

    @Column(nullable = true, columnDefinition = "timestamp")
    private Date fechaFin;

    @Column(name = "area", nullable = true)
    private String area;

    @Column( nullable = true)
    private Float montoPrevisto;

    @Column(nullable = true)
    private Long empleosDirectos;

    @Column(nullable = true)
    private Long empleosIndirectos;

    @Column(nullable = true)
    private String oficioTurno;

    @Column(name = "estatus", nullable = true)
    private String estatus;

    @Column(nullable = true)
    private String observaciones;

    @Column
    private Boolean activo;

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false, columnDefinition = "timestamp")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_proyecto__a_proyecto_historico",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_historico_id")
    )
    private List<ProjectHistoryEntity> historico = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "a_proyecto__a_tarea",
            joinColumns = {@JoinColumn(name = "proyecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "tarea_id")}
    )
    private List<TaskEntity> proyectoTareas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "a_proyecto__a_proyecto_colaborador",
            joinColumns = {@JoinColumn(name = "proyecto_id")},
            inverseJoinColumns = {@JoinColumn(name = "colaborador_id")}
    )
    private List<ProjectColaboratorEntity> colaboradores = new ArrayList<>();


    public void agregarTarea(TaskEntity tarea) {
        this.proyectoTareas.add(tarea);
    }

    public void agregarColaborador(ProjectColaboratorEntity proyectoColaborador) { this.colaboradores.add(proyectoColaborador); }

    public void agregarHistorico(ProjectHistoryEntity historico) {
        this.historico.add(historico);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsEntity getTipoId() {
        return tipoId;
    }

    public void setTipoId(CatalogsEntity tipoId) {
        this.tipoId = tipoId;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EnterpriseEntity getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(EnterpriseEntity empresaId) {
        this.empresaId = empresaId;
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

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public List<ProjectHistoryEntity> getHistorico() {
        return historico;
    }

    public void setHistorico(List<ProjectHistoryEntity> historico) {
        this.historico = historico;
    }

    public List<TaskEntity> getProyectoTareas() {
        return proyectoTareas;
    }

    public void setProyectoTareas(List<TaskEntity> proyectoTareas) {
        this.proyectoTareas = proyectoTareas;
    }

    public List<ProjectColaboratorEntity> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<ProjectColaboratorEntity> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
