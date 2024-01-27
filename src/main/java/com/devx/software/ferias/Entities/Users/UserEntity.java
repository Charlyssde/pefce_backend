package com.devx.software.ferias.Entities.Users;

import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Logs.SessionEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Shared.DomicilioEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_usuarios")
public class UserEntity {
    @OneToMany(mappedBy = "usuario")
    private final List<SessionEntity> sesiones = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, columnDefinition = "varchar(200) unique")
    private String nombre;
    @Column(name = "email", nullable = false, columnDefinition = "varchar(200) unique")
    private String email;
    @Column(name = "password", nullable = true, columnDefinition = "varchar(250)")
    private String password = null;
    @Column(name = "telefono", nullable = true, columnDefinition = "varchar(15)")
    private String telefono = null;
    @Column(name = "sexo", nullable = true, columnDefinition = "varchar(1)")
    private String sexo = null;
    @Column(name = "recibir_publicidad", nullable = false, columnDefinition = "boolean default true")
    private Boolean recibirPublicidad;
    @Column(name = "estatus", nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column(name = "updated_at", nullable = true, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "a_usuario__a_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<ProfileEntity> perfiles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "a_usuario__a_domicilio", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "domicilio_id"))
    private List<DomicilioEntity> domicilios = new ArrayList<>();

    @ManyToMany(mappedBy = "usuariosAgenda")
    private List<AgendaEntity> agenda;

    public void addPerfil(ProfileEntity profileEntity) {
        this.perfiles.add(profileEntity);
    }

    public void addDomicilio(DomicilioEntity domicilioEntity) {
        this.domicilios.add(domicilioEntity);
    }

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

    public List<ProfileEntity> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<ProfileEntity> perfiles) {
        this.perfiles = perfiles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<DomicilioEntity> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<DomicilioEntity> domicilios) {
        this.domicilios = domicilios;
    }
}
