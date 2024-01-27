package com.devx.software.ferias.DTOs.Capacitacion;

public class ContactoPageCsvDTO {
    private String correoDirectorio;
    private String nombre;
    private String sexo;
    private String correo;
    private String correoAlternativo;
    private Boolean recibirCorreos;
    private String telefono;
    private String direccion;
    private String puesto;
    private String departamento;

    public String getCorreoDirectorio() {
        return correoDirectorio;
    }

    public void setCorreoDirectorio(String correoDirectorio) {
        this.correoDirectorio = correoDirectorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }

    public Boolean getRecibirCorreos() {
        return recibirCorreos;
    }

    public void setRecibirCorreos(Boolean recibirCorreos) {
        this.recibirCorreos = recibirCorreos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
