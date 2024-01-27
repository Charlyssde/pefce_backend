package com.devx.software.ferias.Repositories.Tasks;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByUsuarioId(Long usuarioId);

    List<TaskEntity> findAllByUsuarioIdId(Long usuarioId);

    @Query(
            value = "select * from a_tareas t \n" +
                    "where usuario_id = ?1\n" +
                    "and estatus = false\n" +
                    "order by fecha_inicio DESC",
            nativeQuery = true
    )

    List<TaskEntity> findPendingTasksByUser(Long usuarioId);

    @Query(
            value = "select * from a_tareas t \n" +
                    "where estatus = false\n" +
                    "order by fecha_inicio DESC",
            nativeQuery = true
    )
    List<TaskEntity> findAllPendingTasks();

    @Query(value = "select t.* from minutas__tareas mt inner join a_tareas t on mt.tarea_id = t.id where mt.minuta_id = ?1 order by created_at desc limit 1;", nativeQuery = true)
    TaskEntity findLastInsertedTareaOnMinutas(Long idMinuta);

    Page<TaskEntity> findAllByOrderByTareaDesc(Pageable pageable);

    @Query(
            value = "SELECT t.* FROM a_tareas t " +
                    "inner join a_tarea__a_usuario ta on t.id = ta.tarea_id " +
                    "WHERE (CAST(:tarea AS text) is null OR (LOWER(t.tarea) LIKE LOWER(CONCAT('%',CAST(:tarea AS text),'%')) " +
                    "OR LOWER(t.tarea) LIKE LOWER(CONCAT('%',CAST(:tarea AS text),'%')))) " +
                    "AND ta.usuario_id = :usuarioId " +
                    /*
                    "AND (CAST(:descripcion AS text) is null OR (LOWER(a_tareas.descripcion) LIKE LOWER(CONCAT('%',CAST(:descripcion AS text),'%')) " +
                    "OR LOWER(a_tareas.descripcion) LIKE LOWER(CONCAT('%',CAST(:descripcion AS text),'%')))) " +
                    "AND (CAST(:entregable AS text) is null OR (LOWER(a_tareas.entregable) LIKE LOWER(CONCAT('%',CAST(:entregable AS text),'%')) " +
                    "OR LOWER(a_tareas.entregable) LIKE LOWER(CONCAT('%',CAST(:entregable AS text),'%')))) " +
                    "AND (CAST(:fechaInicio AS text) is null OR (LOWER(a_tareas.fechaInicio) LIKE LOWER(CONCAT('%',CAST(:fechaInicio AS text),'%')) " +
                    "OR LOWER(a_tareas.fechaInicio) LIKE LOWER(CONCAT('%',CAST(:fechaInicio AS text),'%')))) " +
                    "AND (CAST(:fechaTermino AS text) is null OR (LOWER(a_tareas.fechaTermino) LIKE LOWER(CONCAT('%',CAST(:fechaTermino AS text),'%')) " +
                    "OR LOWER(a_tareas.fechaTermino) LIKE LOWER(CONCAT('%',CAST(:fechaTermino AS text),'%')))) " +
                    "AND (CAST(:estatus AS text) is null OR (LOWER(a_tareas.estatus) LIKE LOWER(CONCAT('%',CAST(:estatus AS text),'%')) " +
                    "OR LOWER(a_tareas.estatus) LIKE LOWER(CONCAT('%',CAST(:estatus AS text),'%')))) " +
                    "AND (CAST(:usuarioId AS text) is null OR (LOWER(a_tareas.usuario_id) LIKE LOWER(CONCAT('%',CAST(:usuarioId AS text),'%')) " +
                    "OR LOWER(a_tareas.usuario_id) LIKE LOWER(CONCAT('%',CAST(:usuarioId AS text),'%')))) " +
                    */
                    "ORDER BY t.fecha_termino",
            nativeQuery = true
    )

    Page<TaskEntity> findAllByOrderByTaskAscAndMiscFilters(
            Pageable pageable,
            UserEntity usuarioId,
            String tarea
            /*String descripcion,
            String entregable,
            String fechaInicio,
            String fechaTermino,
            String estatus*/
    );

}
