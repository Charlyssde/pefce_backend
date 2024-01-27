package com.devx.software.ferias.Entities.Agenda;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "a_agenda__a_tarea")
public class AgendaTareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_agenda")
    @JsonBackReference("agenda")
    private AgendaEntity agenda;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tarea")
    @JsonBackReference("tarea")
    private TaskEntity tarea;

    public AgendaEntity getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

    public TaskEntity getTarea() {
        return tarea;
    }

    public void setTarea(TaskEntity tarea) {
        this.tarea = tarea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
