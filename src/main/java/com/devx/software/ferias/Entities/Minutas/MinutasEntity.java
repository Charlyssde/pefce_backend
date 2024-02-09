package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Catalogs.EstadosEntity;
import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "a_minutas")
public class MinutasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_tipo_minuta", nullable = true)
    private CatalogsEntity tipoMinuta;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_estado", nullable = true)
    private EstadosEntity estado;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_municipio", nullable = true)
    private MunicipiosEntity municipio;

    @Column(nullable = true)
    private String folio;

    @Column(nullable = true)
    private String asunto;

    @Column(nullable = true)
    private String objetivo;

    @Column(nullable = true)
    private String sede;

    @Column(nullable = true)
    private Date fecha;

    @Column(nullable = true)
    private String comentarios;

    @Column(nullable = true)
    private String puntosTratados;

    @Column(nullable = true)
    private String codigoPostal;

    @Column(nullable = true)
    private String ciudad;

    @Column(nullable = true)
    private Date createdAt;

    @Column(nullable = true)
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(
            name = "a_minuta__a_tarea",
            joinColumns = @JoinColumn(name = "minuta_id"),
            inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )
    private List<TaskEntity> minutaTareas = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(
            name = "a_minuta__a_usuario",
            joinColumns = @JoinColumn(name = "minuta_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UserEntity> minutaUsuarios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(
            name = "a_minuta__a_temas",
            joinColumns = @JoinColumn(name = "minuta_id"),
            inverseJoinColumns = @JoinColumn(name = "catalogo_id")
    )
    private List<CatalogsEntity> minutaTemas = new ArrayList<>();

    @OneToMany(mappedBy = "minuta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<MinutaArchivoEntity> minutaArchivos;

    @OneToOne(optional = true)
    @JoinColumn(name = "id", nullable = true)
    private ProjectEntity proyecto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsEntity getTipoMinuta() {
        return tipoMinuta;
    }

    public void setTipoMinuta(CatalogsEntity tipoMinuta) {
        this.tipoMinuta = tipoMinuta;
    }

    public EstadosEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadosEntity estado) {
        this.estado = estado;
    }

    public MunicipiosEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipiosEntity municipio) {
        this.municipio = municipio;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getPuntosTratados() {
        return puntosTratados;
    }

    public void setPuntosTratados(String puntosTratados) {
        this.puntosTratados = puntosTratados;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public List<TaskEntity> getMinutaTareas() {
        return minutaTareas;
    }

    public void setMinutaTareas(List<TaskEntity> minutaTareas) {
        this.minutaTareas = minutaTareas;
    }

    public List<UserEntity> getMinutaUsuarios() {
        return minutaUsuarios;
    }

    public void setMinutaUsuarios(List<UserEntity> minutaUsuarios) {
        this.minutaUsuarios = minutaUsuarios;
    }

    public List<CatalogsEntity> getMinutaTemas() {
        return minutaTemas;
    }

    public void setMinutaTemas(List<CatalogsEntity> minutaTemas) {
        this.minutaTemas = minutaTemas;
    }

    public Set<MinutaArchivoEntity> getMinutaArchivos() {
        return minutaArchivos;
    }

    public void setMinutaArchivos(Set<MinutaArchivoEntity> minutaArchivos) {
        this.minutaArchivos = minutaArchivos;
    }

    public ProjectEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProjectEntity proyecto) {
        this.proyecto = proyecto;
    }
}
