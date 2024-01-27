package com.devx.software.ferias.Repositories.Encuestas;

import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestasRepository extends JpaRepository<EncuestaEntity, Long> {

    @Query(
            value = "select * from a_encuestas t \n" +
                    "where estatus = false\n" +
                    "order by fecha_inicio DESC",
            nativeQuery = true
    )
    List<EncuestaEntity> findAllPendingEncuestas();

    Page<EncuestaEntity> findAllByOrderByTituloDesc(Pageable pageable);

    @Query(
            value = "SELECT t.* FROM a_encuestas t " +
                    "WHERE (CAST(:titulo AS text) is null OR (LOWER(t.titulo) LIKE LOWER(CONCAT('%',CAST(:titulo AS text),'%')) " +
                    "OR LOWER(t.titulo) LIKE LOWER(CONCAT('%',CAST(:titulo AS text),'%')))) " +
                    "ORDER BY t.titulo",
            nativeQuery = true
    )

    Page<EncuestaEntity> findAllByOrderByTituloAsc(
            Pageable pageable,
            String titulo
            /*String descripcion,
            String entregable,
            String fechaInicio,
            String fechaTermino,
            String estatus*/
    );

}
