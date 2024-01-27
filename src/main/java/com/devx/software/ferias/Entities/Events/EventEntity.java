package com.devx.software.ferias.Entities.Events;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_eventos")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String folio;
    /*
    @Column(nullable = true)
    private Long tipo;
   *
     */
    @OneToOne
    @JoinColumn(name = "tipo", nullable = true)
    private CatalogsEntity tipoId;


    @Column(nullable = true)
    private Long estatus;
    /*
    @OneToOne
    @JoinColumn(name = "area", nullable = true)
    private CatalogsEntity area;
     */
    @OneToOne
    @JoinColumn(name = "responsable_id", nullable = true)
    private UserEntity responsableId;

    @Column(nullable = true)
    private String nombreEvento;
    @Column(nullable = true)
    private String descripcion;
    @Column(nullable = true)
    private String ponentes;
    @Column(nullable = true)
    private Date fechaInicio;
    @Column(nullable = true)
    private Date fechaFin;
    @Column(nullable = true)
    private String modalidad;
    @Column(nullable = true)
    private String privacidad;
    @Column(nullable = true)
    private String observaciones;
    /*
    @Column(nullable = true)
    private String pathfileCover;
    @Column(nullable = true)
    private String pathfileVideo;

     */
    @Column(nullable = true)
    private Boolean activo;
    @Column(nullable = true)
    private Date createdAt;
    @Column(nullable = true)
    private Date updatedAt;

    @Column(nullable = true)
    private String sede;

    @Column(nullable = true)
    private String domicilio;

    @Column(nullable = true)
    private String archivoimagen;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_evento__a_archivo",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "archivo_id")
    )
    private List<FileEntity> archivo = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_evento__a_participantes",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UserEntity> usuarios = new ArrayList<>();

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

    public CatalogsEntity getTipoId() {
        return tipoId;
    }

    public void setTipoId(CatalogsEntity tipoId) {
        this.tipoId = tipoId;
    }

    public Long getEstatus() {
        return estatus;
    }

    public void setEstatus(Long estatus) {
        this.estatus = estatus;
    }

    public UserEntity getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(UserEntity responsableId) {
        this.responsableId = responsableId;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPonentes() {
        return ponentes;
    }

    public void setPonentes(String ponentes) {
        this.ponentes = ponentes;
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getArchivoimagen() {
        return archivoimagen;
    }

    public void setArchivoimagen(String archivoimagen) {
        this.archivoimagen = archivoimagen;
    }

    public List<FileEntity> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<FileEntity> archivo) {
        this.archivo = archivo;
    }

    public List<UserEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public void addUsuarios(UserEntity userEntity) {
        this.usuarios.add(userEntity);
    }

    public void addArchivo(FileEntity fileEntity){
        this.archivo.add(fileEntity);
    }
}
