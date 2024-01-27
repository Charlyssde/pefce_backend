package com.devx.software.ferias.Entities.Requests;

import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_solicitudes_vinculacion")
public class RequestLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_vinculacion")
    private String tipoVinculacion;

    @OneToOne(optional = true)
    @JoinColumn(name = "contacto_empresa_id", nullable = true)
    private UserEntity contactoEmpresaId;

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

    public String getTipoVinculacion() {
        return tipoVinculacion;
    }

    public void setTipoVinculacion(String tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
    }

    public UserEntity getContactoEmpresaId() {
        return contactoEmpresaId;
    }

    public void setContactoEmpresaId(UserEntity contactoEmpresaId) {
        this.contactoEmpresaId = contactoEmpresaId;
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
