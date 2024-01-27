package com.devx.software.ferias.Entities.Enterprises;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_empresa_solicitudes_acceso")
public class EnterpriseAccessRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estatus", nullable = false)
    private Boolean status;

    @Column(name = "mensaje", nullable = false, columnDefinition = "longtext")
    private String message;

    @Column(name = "created_at", nullable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @JsonBackReference
    @ManyToMany(mappedBy = "solicitudesAcceso")
    private List<EnterpriseEntity> enterprises = new ArrayList<EnterpriseEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<EnterpriseEntity> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(List<EnterpriseEntity> enterprises) {
        this.enterprises = enterprises;
    }
}
