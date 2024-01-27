package com.devx.software.ferias.Repositories.Plantillas;

import com.devx.software.ferias.Entities.Plantillas.PlantillasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantillasRepository extends JpaRepository<PlantillasEntity, Long> {

    PlantillasEntity findPlantillasById(Long id);

    List<PlantillasEntity> findAllByUsuarioId(Long usuarioId);

    List<PlantillasEntity> findAllByUsuarioIdId(Long usuarioId);

    @Query(
            value = "select * from a_plantillas t \n" +
                    //"where usuario_id = ?1\n" +
                    //"and estatus = false\n" +
                    "order by nombre DESC",
            nativeQuery = true
    )
    List<PlantillasEntity> findPendingTasksByUser(Long usuarioId);

    @Query(
            value = "select * from a_plantillas t \n" +
                    "where estatus = false\n" +
                    "order by fecha_inicio DESC",
            nativeQuery = true
    )
    List<PlantillasEntity> findAllPendingTasks();

}
