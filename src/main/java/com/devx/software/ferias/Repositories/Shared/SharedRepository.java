package com.devx.software.ferias.Repositories.Shared;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.PaginaContenido.PaginaContenidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SharedRepository extends JpaRepository<PaginaContenidoEntity,Long> {


    @Query(
            value = "(select 'empresas' as name,count(em.*) as number from a_empresas em where em.estatus = true)\n" +
                    "union \n" +
                    "(select 'proyectos' as name,count(p.*) as number from a_proyectos p where p.activo = true)\n" +
                    "union \n" +
                    "(select 'eventos' as name,count(ev.*) as number from a_eventos ev where ev.activo = true)\n" +
                    "union \n" +
                    "(select 'capacitaciones' as name,count(c.*) as number FROM a_capacitaciones c)",
            nativeQuery = true
    )
    List<pefceNumbers> getAllPefceNumbers();

    @Query(
            value = "select e.* " +
                    "from a_eventos e " +
                    "where e.fecha_inicio >= now() " +
                    "order by e.fecha_inicio asc " +
                    "limit 10",
            nativeQuery = true
    )
    List<EventEntity> getLastTenEvents();

    @Query(
            value = "select c.* FROM a_capacitaciones c where c.fecha_inicio >= now() order by c.fecha_inicio asc limit 10",
            nativeQuery = true
    )
    List<CapacitacionesEntity> getLastTenCourses();

}

interface pefceNumbers{
    String getName();
    Long getNumber();
}
