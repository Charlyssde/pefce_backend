package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_minuta__a_tarea")
public class MinutaTareaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "minuta_id")
    @JsonBackReference
    private MinutasEntity minuta;

      @ManyToOne(optional = false)
    @JoinColumn(name = "tarea_id")
    @JsonBackReference("tarea")
    private TaskEntity tarea;
    /*
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;
    */


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

  
    /*
    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

     */

   
    public TaskEntity getTarea() {
        return tarea;
    }

    public void setTarea(TaskEntity tarea) {
        this.tarea = tarea;
    }

 
}
