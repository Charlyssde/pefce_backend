package com.devx.software.ferias.DTOs.Profiles;

public class ProfileMenuNoChildrenDTO {
    private MenuDTO menu;

    private Boolean crear;

    private Boolean leer;

    private Boolean actualizar;

    private Boolean eliminar;

    private Boolean reportar;

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
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
