package com.devx.software.ferias.Entities.Requests;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_solicitudes")
public class RequestEntity {
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

    @OneToOne
    @JoinColumn(name = "perfil_id", nullable = true)
    private ProfileEntity perfilId;

    /*
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_solicitud__a_solicitud_historico",
            joinColumns = {@JoinColumn(name = "solicitudes_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitudes_historico_id")}
    )
    private List<RequestHistoryEntity> historico = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_solicitud__a_solicitud_nave",
            joinColumns = {@JoinColumn(name = "solicitudes_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitudes_naves_id")}
    )
    private List<RequestUnitEntity> naves = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_solicitud__a_solicitud_promocion",
            joinColumns = {@JoinColumn(name = "solicitudes_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitudes_promocion_id")}
    )
    private List<RequestPromotionEntity> promociones = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_solicitud__a_solicitud_vinculacion",
            joinColumns = {@JoinColumn(name = "solicitudes_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitudes_vinculacion_id")}
    )
    private List<RequestLinkEntity> vinculaciones = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_solicitud__a_solicitud_asistencia_evento",
            joinColumns = {@JoinColumn(name = "solicitudes_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitudes_asistencia_evento_id")}
    )
    private List<RequestEventAttendanceEntity> asistenciaEventos = new ArrayList<>();

    public void addHistorico(RequestHistoryEntity historico) {
        this.historico.add(historico);
    }

    public void addNave(RequestUnitEntity nave) {
        this.naves.add(nave);
    }

    public void deleteNave(RequestUnitEntity nave) {
        this.naves.remove(nave);
    }

    public void addPromocion(RequestPromotionEntity promocion) {
        this.promociones.add(promocion);
    }

    public void deletePromocion(RequestPromotionEntity promocion) {
        this.promociones.remove(promocion);
    }

    public void addVinculacion(RequestLinkEntity vinculacion) {
        this.vinculaciones.add(vinculacion);
    }

    public void deleteVinculacion(RequestLinkEntity vinculacion) {
        this.vinculaciones.remove(vinculacion);
    }

    public void addAsistenciaEvento(RequestEventAttendanceEntity asistencia) {
        this.asistenciaEventos.add(asistencia);
    }

    public void deleteAsistenciaEvento(RequestEventAttendanceEntity asistencia) {
        this.asistenciaEventos.remove(asistencia);
    }

     */


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

    /*
    public List<RequestHistoryEntity> getHistorico() {
        return historico;
    }

    public void setHistorico(List<RequestHistoryEntity> historico) {
        this.historico = historico;
    }

    public List<RequestUnitEntity> getNaves() {
        return naves;
    }

    public void setNaves(List<RequestUnitEntity> naves) {
        this.naves = naves;
    }

    public List<RequestPromotionEntity> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<RequestPromotionEntity> promociones) {
        this.promociones = promociones;
    }

    public List<RequestLinkEntity> getVinculaciones() {
        return vinculaciones;
    }

    public void setVinculaciones(List<RequestLinkEntity> vinculaciones) {
        this.vinculaciones = vinculaciones;
    }

    public List<RequestEventAttendanceEntity> getAsistenciaEventos() {
        return asistenciaEventos;
    }

    public void setAsistenciaEventos(List<RequestEventAttendanceEntity> asistenciaEventos) {
        this.asistenciaEventos = asistenciaEventos;
    }
    */

    public ProfileEntity getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(ProfileEntity perfilId) {
        this.perfilId = perfilId;
    }
}
