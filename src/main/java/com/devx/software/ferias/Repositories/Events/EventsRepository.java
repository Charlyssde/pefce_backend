package com.devx.software.ferias.Repositories.Events;

import com.devx.software.ferias.Entities.Events.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<EventEntity, Long> {
    EventEntity findEventoById(Long id);

    Long countAllByActivoTrue();

    @Query(
        value = "select e.* from a_eventos e where fecha_inicio > current_date", nativeQuery = true
    )
    List<EventEntity> findAllByStatus();

    @Query(
            value = "delete FROM a_evento__a_participantes ep " +
                    "WHERE CAST(ep.evento_id as text) = CAST(:eventoId as text) " +
                    "AND CAST(ep.usuario_id as text) = CAST(:usuarioId as text) ",
            nativeQuery = true
    )
    Void cancelarParticipacion(
            Long eventoId,
            Long usuarioId
    );

    @Query(value = "SELECT x.id,x.fecha_inicio fechaInicio,x.fecha_fin fechaFin,x.nombre_evento nombreEvento,x.descripcion,x.pathfile_cover pathfileCover,x.activo,x.subarea, x.estatus "
            + "FROM a_eventos x "
            + "WHERE x.fecha_fin >= CURRENT_TIMESTAMP "
            + "AND x.modalidad = 'virtual' "
            + "AND x.privacidad = 'publico' "
            + "ORDER BY x.fecha_fin ASC", nativeQuery = true)
    List<EventosHoy> getByFechaFinTimeAfterToday();


    @Query(value = "SELECT sexo, count(*)  \n" +
            "FROM a_eventos AS s\n" +
            " join a_evento__a_participantes as ap on ap.evento_id = s.id" +
            " join a_usuarios as au on au.id = ap.usuario_id  \n" +
            " group by au.sexo ", nativeQuery = true)
    List<Object[]> obtenerTotalPorSexo();

    @Query(value = "    SELECT ad.municipio, count(au.id)\n" +
            "FROM a_eventos AS s\n" +
            " join a_evento__a_participantes as ap on ap.evento_id = s.id" +
            " join a_usuarios as au on au.id = ap.usuario_id  \n" +            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresa__a_domicilio aead on aead.empresa_id = aeac.empresa_id\n" +
            "    join a_domicilios ad on ad.id =aead.domicilio_id\n" +
            "    group by ad.municipio", nativeQuery = true)
    List<Object[]> obtenerTotalPorMunicipio();

    @Query(
            value = "select e.* " +
                    "from a_eventos e " +
                    "order by e.fecha_inicio desc " +
                    "limit 10",
            nativeQuery = true
    )
    List<EventEntity> getLastTenEvents();

    @Query(value = "SELECT ac.nombre , count(au.id)\n" +
            "FROM a_eventos AS s\n" +
            " join a_evento__a_participantes as ap on ap.evento_id = s.id" +
            " join a_usuarios as au on au.id = ap.usuario_id  \n" +            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresas ae on ae.id = aeac.empresa_id\n" +
            "    join a_catalogos ac on ac.id = ae.sector_id\n" +
            "    group by ac.nombre", nativeQuery = true)
    List<Object[]> obtenerTotalPorSector();

    @Query(value = "select e.* from a_eventos", nativeQuery = true)
    List<EventEntity> getAllByUser(long id);

    interface EventosHoy {
        Long getId();

        Date getFechaInicio();

        Date getFechaFin();

        String getNombreEvento();

        String getDescripcion();

        String getSubarea();

        String getPathfileCover();

        Boolean getActivo();

        String getEstatus();
    }
    
    @Query(value = "select ae.* from a_eventos ae\n"
            + "INNER JOIN eventos_encuestas ee ON ae.id = ee.id_evento\n"
            + "where ee.id_encuesta = :id", nativeQuery = true)
    List<EventEntity> getAllByEvent(long id);
    
}
