package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "a_usuario__a_minuta")
public class UsuarioMinutaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "minuta_id")
    @JsonBackReference
    private MinutasEntity minuta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MinutasEntity getMinuta() {
        return minuta;
    }

    public void setMinuta(MinutasEntity minuta) {
        this.minuta = minuta;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }
}
