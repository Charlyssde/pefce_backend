package com.devx.software.ferias.Entities.Profiles;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "a_perfil__a_menu")
public class ProfileMenuEntity {
    @EmbeddedId
    private ProfileMenuKey profileMenuKey;
    @ManyToOne(optional = false)
    @MapsId("menu")
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
    @ManyToOne(optional = false)
    @MapsId("perfil")
    @JoinColumn(name = "perfil_id")
    @JsonBackReference
    private ProfileEntity perfil;
    @Column
    private Boolean crear;
    @Column
    private Boolean leer;
    @Column
    private Boolean actualizar;
    @Column
    private Boolean eliminar;
    @Column
    private Boolean reportar;

    public ProfileMenuEntity() {
    }

    public ProfileMenuEntity(ProfileMenuKey profileMenuKey, Boolean crear, Boolean leer, Boolean actualizar, Boolean eliminar, Boolean reportar) {
        this.profileMenuKey = profileMenuKey;
        this.crear = crear;
        this.leer = leer;
        this.actualizar = actualizar;
        this.eliminar = eliminar;
        this.reportar = reportar;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public ProfileEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(ProfileEntity perfil) {
        this.perfil = perfil;
    }

    public Boolean getCrear() {
        return crear;
    }

    public void setCrear(Boolean crear) {
        this.crear = crear;
    }

    public Boolean getLeer() {
        return leer;
    }

    public void setLeer(Boolean leer) {
        this.leer = leer;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

    public Boolean getReportar() {
        return reportar;
    }

    public void setReportar(Boolean reportar) {
        this.reportar = reportar;
    }


}
