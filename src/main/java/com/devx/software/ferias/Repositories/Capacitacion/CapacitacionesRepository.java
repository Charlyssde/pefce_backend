package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CapacitacionesRepository extends JpaRepository<CapacitacionesEntity, Long> {
    CapacitacionesEntity findCapacitacionById(Long id);

    Long countAllByActivoTrue();

    //@Query(value = "SELECT c.* FROM a_capacitaciones c INNER JOIN a_capacitacion__a_usuario uc on uc.id_capacitacion = c.id WHERE uc.id_usuario = ?1", nativeQuery = true)
    @Query(value = "select c.* FROM a_capacitaciones c where c.id in (select c.id FROM a_capacitaciones c inner join a_capacitacion__a_usuario uc on uc.id_capacitacion = c.id where uc.id_usuario = ?1)", nativeQuery = true)
    List<CapacitacionesEntity> findCapacitacionesRegistradas(Long idUsuario);

    //@Query(value = "SELECT c.* FROM a_capacitaciones c LEFT JOIN a_capacitacion__a_usuario uc on uc.id_capacitacion = c.id WHERE c.activo = true AND c.tipo IN (2,4,6) AND (uc.id_usuario IS NULL OR uc.id_usuario != ?1) GROUP BY c.id", nativeQuery = true)
    @Query(value = "select c.* FROM a_capacitaciones c where c.id not in (select c.id FROM a_capacitaciones c inner join a_capacitacion__a_usuario uc on uc.id_capacitacion = c.id where uc.id_usuario = ?1) AND c.tipo IN (2,4,6)", nativeQuery = true)
    List<CapacitacionesEntity> findCapacitacionesNoRegistradas(Long idUsuario);

    @Query(value = "SELECT count(p.id) FROM a_capacitaciones c INNER JOIN a_capacitacion__a_modulos m ON C.id = m.id_capacitacion INNER JOIN a_capacitacion__a_temas t ON m.id = t.id_modulo INNER JOIN a_capacitacion__a_preguntas p ON t.id = p.id_tema WHERE c.id = ?1", nativeQuery = true)
    Long countPreguntasPorCapacitacion(Long idCapacitacion);

    @Query(value = "SELECT count(up.id) FROM a_capacitaciones c INNER JOIN a_capacitacion__a_modulos m ON C.id = m.id_capacitacion INNER JOIN a_capacitacion__a_temas t ON m.id = t.id_modulo INNER JOIN a_capacitacion__a_preguntas p ON t.id = p.id_tema INNER JOIN a_capacitacion__a_usuario_pregunta up ON p.id = up.id_pregunta WHERE c.id = ?1 AND up.id_usuario = ?2 AND p.respuesta_correcta = up.respuesta", nativeQuery = true)
    Long countPreguntasRespondidasPorCapacitacion(Long idCapacitacion, Long idUsuario);

    @Query(value = "select count(c.id) FROM a_capacitaciones c inner join a_capacitacion__a_usuario uc on uc.id_capacitacion = c.id where uc.id_capacitacion = ?1 and uc.id_usuario = ?2", nativeQuery = true)
    Long checkIfCapacitacionRegistrada(Long idCapacitacion, Long idUsuario);

    @Query(value = "SELECT "
            + " x.id,x.fecha_inicio fechaInicio,"
            + " x.fecha_fin fechaFin,"
            + " x.nombre,x.descripcion,"
            + " x.activo,x.subarea "
            + "FROM a_capacitaciones x "
            + "WHERE x.fecha_fin >= CURRENT_TIMESTAMP "
            + "ORDER BY x.fecha_fin ASC", nativeQuery = true)
    List<CapacitacionesHoy> getByFechaFinTimeAfterToday();

    @Query(
            value = "select ac.* FROM a_capacitaciones ac order by ac.fecha_inicio desc limit 10",
            nativeQuery = true
    )
    List<CapacitacionesEntity> getLastTenCourses();


    @Query(value = "SELECT sexo, count(*)  \n" +
            "FROM a_capacitaciones AS s\n" +
            "join a_capacitacion__a_usuario as cc on cc.id_capacitacion = s.id  \n" +
            "join a_usuarios as au on au.id = cc.id_usuario  \n" +
            "group by au.sexo ", nativeQuery = true)
    List<Object[]> obtenerTotalPorSexo();

    @Query(value = "SELECT ad.municipio, count(au.id)\n" +
            " FROM a_capacitaciones AS s\n" +
            " join a_capacitacion__a_usuario as cc on cc.id_capacitacion = s.id  \n" +
            " join a_usuarios as au on au.id = cc.id_usuario  \n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresa__a_domicilio aead on aead.empresa_id = aeac.empresa_id\n" +
            "    join a_domicilios ad on ad.id =aead.domicilio_id\n" +
            "    group by ad.municipio", nativeQuery = true)
    List<Object[]> obtenerTotalPorMunicipio();

    @Query(value = "SELECT ac.nombre , count(au.id)\n" +
            " FROM a_capacitaciones AS s\n" +
            " join a_capacitacion__a_usuario as cc on cc.id_capacitacion = s.id  \n" +
            " join a_usuarios as au on au.id = cc.id_usuario  \n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresas ae on ae.id = aeac.empresa_id\n" +
            "    join a_catalogos ac on ac.id = ae.sector_id\n" +
            "    group by ac.nombre", nativeQuery = true)
    List<Object[]> obtenerTotalPorSector();

    interface CapacitacionesHoy {
        Long getId();

        Date getFechaInicio();

        Date getFechaFin();

        String getNombre();

        String getDescripcion();

        String getSubarea();

        Boolean getActivo();
    }
}
