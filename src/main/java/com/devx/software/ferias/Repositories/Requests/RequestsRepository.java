package com.devx.software.ferias.Repositories.Requests;

import com.devx.software.ferias.DTOs.Reportes.ReportesResponse;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Requests.RequestEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsRepository extends JpaRepository<RequestEntity, Long> {

    Page<RequestEntity> findAllByOrderByDescripcionAsc(
            Pageable pageable
    );


    @Query(
            value = "SELECT a_solicitudes.* FROM a_solicitudes " +
                    "WHERE CAST(a_solicitudes.usuario_encargado_id as text) = CAST(:usuarioId as text) " +
                    "ORDER BY a_solicitudes.id DESC",
            nativeQuery = true
    )
    Page<RequestEntity> buscarSolicitudesPorResponsable(
            Pageable pageable,
            Long usuarioId
            //String usuarioId
    );

    @Query(
            value = "SELECT a_solicitudes.* FROM a_solicitudes " +
                    "WHERE CAST(a_solicitudes.usuario_solicitante_id as text) = CAST(:usuarioId as text) " +
                    "ORDER BY a_solicitudes.id DESC",
            nativeQuery = true
    )
    Page<RequestEntity> buscarSolicitudesPorSolicitante(
            Pageable pageable,
            Long usuarioId
            //String usuarioId
    );

    @Query(value = "SELECT sexo, count(*)  \n" +
            "FROM a_solicitudes AS s\n" +
            "join a_usuarios as au on au.id = s.usuario_solicitante_id  \n" +
            "group by au.sexo ", nativeQuery = true)
    List<Object[]> obtenerTotalPorSexo();

    @Query(value = "    SELECT ad.municipio, count(au.id)\n" +
            "    FROM a_solicitudes AS s\n" +
            "    join a_usuarios au on au.id = s.usuario_solicitante_id\n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresa__a_domicilio aead on aead.empresa_id = aeac.empresa_id\n" +
            "    join a_domicilios ad on ad.id =aead.domicilio_id\n" +
            "    group by ad.municipio", nativeQuery = true)
    List<Object[]> obtenerTotalPorMunicipio();

    @Query(value = "SELECT ac.nombre , count(au.id)\n" +
            "    FROM a_solicitudes AS s\n" +
            "    join a_usuarios au on au.id = s.usuario_solicitante_id\n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresas ae on ae.id = aeac.empresa_id\n" +
            "    join a_catalogos ac on ac.id = ae.sector_id\n" +
            "    group by ac.nombre", nativeQuery = true)
    List<Object[]> obtenerTotalPorSector();



}

