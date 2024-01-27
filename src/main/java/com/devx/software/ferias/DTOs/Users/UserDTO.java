package com.devx.software.ferias.DTOs.Users;

public class UserDTO {

    private Long id;

    private String nombre;

    private String email;

    private String telefono;

    private String sexo;

    private Boolean recibirPublicidad;

    private Boolean estatus;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getRecibirPublicidad() {
        return recibirPublicidad;
    }

    public void setRecibirPublicidad(Boolean recibirPublicidad) {
        this.recibirPublicidad = recibirPublicidad;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
