package com.devx.software.ferias.Entities.Plantillas;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Plantillas.PlantillasTareasEntity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_plantillas")
public class PlantillasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false)
    private Long id;

    @Column(nullable = true)
    private String nombre;

    @Column(nullable = true)
    private Date createdAt;

    @Column(nullable = true)
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UserEntity usuarioId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "a_plantillas__a_plantillas_tareas",
            joinColumns = {@JoinColumn(name = "plantilla_id")},
            inverseJoinColumns = {@JoinColumn(name = "plantilla_tareas_id")}
    )

    private List<PlantillasTareasEntity> tareas = new ArrayList<>();

    public void addPlantillasTareas(PlantillasTareasEntity tareas) {
        this.tareas.add(tareas);
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

    public UserEntity getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UserEntity usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<PlantillasTareasEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<PlantillasTareasEntity> tareas) {
        this.tareas = tareas;
    }

}