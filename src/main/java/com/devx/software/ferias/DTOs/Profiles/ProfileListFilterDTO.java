package com.devx.software.ferias.DTOs.Profiles;

import com.devx.software.ferias.DTOs.Users.UserListDTO;

import java.util.List;

public class ProfileListFilterDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String area;
    private Long nivel;
    private List<UserListDTO> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public List<UserListDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserListDTO> usuarios) {
        this.usuarios = usuarios;
    }
}
