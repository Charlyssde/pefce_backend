package com.devx.software.ferias.DTOs.Requests;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import javax.persistence.*;
import java.util.Date;

public class RequestListDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folio", nullable = true)
    private String folio;

    @Column(name = "tipo_solicitud_id")
    private Long tipoSolicitudId;

    @OneToOne
    @JoinColumn(name = "usuario_solicitante_id")
    private UserEntity usuarioSolicitanteId;

    @OneToOne
    @JoinColumn(name = "areas_id")
    private CatalogsEntity areasId;

    @OneToOne
    @JoinColumn(name = "usuario_encargado_id", nullable = true)
    private UserEntity usuarioEncargadoId;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "estatus", nullable = true)
    private Boolean estatus;

    @Column(name = "comentario", nullable = true)
    private String comentario;

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

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Long getTipoSolicitudId() {
        return tipoSolicitudId;
    }

    public void setTipoSolicitudId(Long tipoSolicitudId) {
        this.tipoSolicitudId = tipoSolicitudId;
    }

    public UserEntity getUsuarioSolicitanteId() {
        return usuarioSolicitanteId;
    }

    public void setUsuarioSolicitanteId(UserEntity usuarioSolicitanteId) {
        this.usuarioSolicitanteId = usuarioSolicitanteId;
    }

    public CatalogsEntity getAreasId() {
        return areasId;
    }

    public void setAreasId(CatalogsEntity areasId) {
        this.areasId = areasId;
    }

    public UserEntity getUsuarioEncargadoId() {
        return usuarioEncargadoId;
    }

    public void setUsuarioEncargadoId(UserEntity usuarioEncargadoId) {
        this.usuarioEncargadoId = usuarioEncargadoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
