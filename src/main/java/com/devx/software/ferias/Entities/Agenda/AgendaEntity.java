package com.devx.software.ferias.Entities.Agenda;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_agenda")
public class AgendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = true, columnDefinition = "varchar")
    private String titulo;

    @Column(name = "descripcion", nullable = true, columnDefinition = "text")
    private String descripcion;

    @Column(name = "inicio", nullable = false, columnDefinition = "timestamp")
    private Date inicio;

    @Column(name = "fin", nullable = true, columnDefinition = "timestamp")
    private Date fin;

    @Column(name = "dia_completo", nullable = true, columnDefinition = "boolean")
    private Boolean diaCompleto;

    @Column(name = "tipo_evento", nullable = false, columnDefinition = "varchar default 'personal'")
    private String tipoEvento;

    @Column(name = "estatus", nullable = true, columnDefinition = "boolean default true")
    private Boolean estatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default timestamp")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "timestamp")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_agenda__a_usuario",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UserEntity> usuariosAgenda = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST})
    @JoinTable(
            name = "a_agenda__a_tarea",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )
    private List<TaskEntity> tareasAgenda = new ArrayList<>();

    public AgendaEntity() {}

    public AgendaEntity(
            Long id,
            String titulo,
            String descripcion,
            Date inicio,
            Date fin,
            Boolean diaCompleto,
            String tipoEvento,
            Boolean estatus,
            Date createdAt,
            Date updatedAt,
            List<UserEntity> usuariosAgenda,
            List<TaskEntity> tareasAgenda
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.diaCompleto = diaCompleto;
        this.tipoEvento = tipoEvento;
        this.estatus = estatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.usuariosAgenda = usuariosAgenda;
        this.tareasAgenda = tareasAgenda;
    }

    public void addUsuario(UserEntity userEntity) {
        this.usuariosAgenda.add(userEntity);
    }

    public void addTarea(TaskEntity taskEntity){ this.tareasAgenda.add(taskEntity); }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Boolean getDiaCompleto() {
        return diaCompleto;
    }

    public void setDiaCompleto(Boolean diaCompleto) {
        this.diaCompleto = diaCompleto;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    public List<UserEntity> getUsuariosAgenda() {
        return usuariosAgenda;
    }

    public void setUsuariosAgenda(List<UserEntity> usuariosAgenda) {
        this.usuariosAgenda = usuariosAgenda;
    }

    public List<TaskEntity> getTareasAgenda() { return tareasAgenda; }

    public void setTareasAgenda(List<TaskEntity> tareasAgenda) { this.tareasAgenda = tareasAgenda; }
}
