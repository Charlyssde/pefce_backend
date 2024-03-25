package com.devx.software.ferias.Entities.Events;

import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_evento__a_participantes")
public class EventUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id")
    private EventEntity evento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_municipio")
    private MunicipiosEntity municipio;

    @Column(nullable = true)
    private int males;

    @Column(nullable = true)
    private int females;

    @Column(nullable = true)
    private String estatus;

    @Column(nullable = true)
    private Boolean activo;

    @Column(nullable = true)
    private Date createdAt;

    @Column(nullable = true)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventEntity getEvento() {
        return evento;
    }

    public void setEvento(EventEntity evento) {
        this.evento = evento;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
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

    public MunicipiosEntity getMunicipio(){
        return this.municipio;
    }

    public void setMunicipo(MunicipiosEntity municipiosEntity){
        this.municipio = municipiosEntity;
    }

    public int getMales(){
        return this.males;
    }

    public void setMales(int males){
        this.males = males;
    }

    public int getFemales(){
        return this.females;
    }

    public void setFemales(int females){
        this.females = females;
    }
}
