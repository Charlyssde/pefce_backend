package com.devx.software.ferias.Repositories.Projects;

import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findProyectosById(Long id);

    @Query(
            value = "SELECT DISTINCT p.* " +
                    "FROM a_proyectos AS p " +
                    "INNER JOIN a_empresas AS e ON p.empresa_id = e.id " +
                    "LEFT JOIN a_proyecto__a_proyecto_colaborador AS apapc ON p.id = apapc.proyecto_id " +
                    "LEFT JOIN a_proyectos_colaborador AS apc ON apapc.colaborador_id = apc.id " +
                    "LEFT JOIN a_usuarios AS au on apc.usuario_id = au.id " +
                    "WHERE " +
                    // Global filters
                    "(" +
                    "CAST(:filtro AS text) is null " +
                    "OR LOWER(p.folio) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.empresa) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.rfc) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    ") " +
                    "AND (CAST(:activo as text) is null OR LOWER(CAST(p.activo AS text)) = LOWER(CAST(:activo AS text))) " +
                    "AND (CAST(:fechaInicio as text) is null OR CAST(p.fecha_inicio as text) >= CAST(:fechaInicio as text)) " +
                    "AND (CAST(:fechaFin as text) is null OR CAST(p.fecha_fin as text) <= CAST(:fechaFin as text)) " +
                    // User|Profile filters
                    "AND (CAST(:perfil as text) is null OR (apc.usuario_id) in (" +
                    "select au2.id " +
                    "from a_usuarios as au2 " +
                    "inner join a_usuario__a_perfil AS auap on auap.usuario_id = au2.id " +
                    "inner join a_perfiles AS ap on auap.perfil_id = ap.id " +
                    "inner join (select ap2.area, ap2.nivel from a_perfiles AS ap2 where cast(ap2.id as text) = cast(:perfil as text)) as profile on profile.area = ap.area and ap.nivel>=profile.nivel" +
                    ")) " +
                    "AND (CAST(:usuario as text) is null or (p.id) in (" +
                    "select p2.id " +
                    "from a_proyectos as p2 " +
                    "inner join a_proyecto__a_proyecto_colaborador apapc2 on apapc2.proyecto_id = p2.id  " +
                    "inner join a_proyectos_colaborador apc2 on apc2.id = apapc2.colaborador_id  " +
                    "where cast(apc2.usuario_id as text) = cast(:usuario as text))" +
                    ") " +
                    // Module specific filters
                    "AND (CAST(:prioridad as text) is null OR CAST(p.prioridad as TEXT) = CAST(:prioridad AS TEXT)) " +
                    "AND (CAST(:estatus as text) is null OR CAST(p.estatus as TEXT) = CAST(:estatus AS TEXT)) " +
                    "AND (CASE " +
                    "when LOWER(CAST(:conResponsable as text)) = 'true' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') >= 1 " +
                    "when LOWER(CAST(:conResponsable as text)) = 'false' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') = 0 " +
                    "else true " +
                    "end) " +
                    //  Enterprise filters
                    "AND (CAST(:regimenFiscal as text) is null OR CAST(e.regimen_fiscal_id as TEXT) = CAST(:regimenFiscal AS TEXT)) " +
                    "AND (CAST(:categoria as text) is null OR CAST(e.categoria_id as TEXT) = CAST(:categoria AS TEXT)) " +
                    "AND (CAST(:sector as text) is null OR CAST(e.sector_id as TEXT) = CAST(:sector AS TEXT)) " +
                    "AND (CAST(:subsector as text) is null OR CAST(e.subsector_id as TEXT) = CAST(:subsector AS TEXT)) " +
                    "AND (CAST(:empresa as text) is null OR CAST(e.id as TEXT) = CAST(:empresa as text)) " +
                    // Institution filter
                    "AND (CAST(:area as text) is null OR CAST(p.area as TEXT) = CAST(:area AS TEXT)) " +
                    "GROUP BY p.id "+
                    "ORDER BY p.id DESC",
            nativeQuery = true
    )
    Page<ProjectEntity> findAllByOrderByIdDesc(
            Pageable pageable,
            // Global specific filters
            String filtro,
            String activo,
            String fechaInicio,
            String fechaFin,
            // User|Profile filters
            String perfil,
            String usuario,
            // Module specific filters
            String prioridad,
            String estatus,
            String conResponsable,
            // Enterprise specific filters
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String empresa,
            // Institution specific filters
            String area
    );


    @Query(
            value = "SELECT DISTINCT p.* " +
                    "FROM a_proyectos AS p " +
                    "INNER JOIN a_empresas AS e ON p.empresa_id = e.id and e.id = :empresaid " +
                    "LEFT JOIN a_proyecto__a_proyecto_colaborador AS apapc ON p.id = apapc.proyecto_id " +
                    "LEFT JOIN a_proyectos_colaborador AS apc ON apapc.colaborador_id = apc.id " +
                    "LEFT JOIN a_usuarios AS au on apc.usuario_id = au.id " +
                    "WHERE " +
                    // Global filters
                    "(" +
                    "CAST(:filtro AS text) is null " +
                    "OR LOWER(p.folio) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.empresa) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.rfc) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    ") " +
                    "AND (CAST(:activo as text) is null OR LOWER(CAST(p.activo AS text)) = LOWER(CAST(:activo AS text))) " +
                    "AND (CAST(:fechaInicio as text) is null OR CAST(p.fecha_inicio as text) >= CAST(:fechaInicio as text)) " +
                    "AND (CAST(:fechaFin as text) is null OR CAST(p.fecha_fin as text) <= CAST(:fechaFin as text)) " +
                    // User|Profile filters
                    "AND (CAST(:perfil as text) is null OR (apc.usuario_id) in (" +
                    "select au2.id " +
                    "from a_usuarios as au2 " +
                    "inner join a_usuario__a_perfil AS auap on auap.usuario_id = au2.id " +
                    "inner join a_perfiles AS ap on auap.perfil_id = ap.id " +
                    "inner join (select ap2.area, ap2.nivel from a_perfiles AS ap2 where cast(ap2.id as text) = cast(:perfil as text)) as profile on profile.area = ap.area and ap.nivel>=profile.nivel" +
                    ")) " +
                    "AND (CAST(:usuario as text) is null or (p.id) in (" +
                    "select p2.id " +
                    "from a_proyectos as p2 " +
                    "inner join a_proyecto__a_proyecto_colaborador apapc2 on apapc2.proyecto_id = p2.id  " +
                    "inner join a_proyectos_colaborador apc2 on apc2.id = apapc2.colaborador_id  " +
                    "where cast(apc2.usuario_id as text) = cast(:usuario as text))" +
                    ") " +
                    // Module specific filters
                    "AND (CAST(:prioridad as text) is null OR CAST(p.prioridad as TEXT) = CAST(:prioridad AS TEXT)) " +
                    "AND (CAST(:estatus as text) is null OR CAST(p.estatus as TEXT) = CAST(:estatus AS TEXT)) " +
                    "AND (CASE " +
                    "when LOWER(CAST(:conResponsable as text)) = 'true' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') >= 1 " +
                    "when LOWER(CAST(:conResponsable as text)) = 'false' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') = 0 " +
                    "else true " +
                    "end) " +
                    //  Enterprise filters
                    "AND (CAST(:regimenFiscal as text) is null OR CAST(e.regimen_fiscal_id as TEXT) = CAST(:regimenFiscal AS TEXT)) " +
                    "AND (CAST(:categoria as text) is null OR CAST(e.categoria_id as TEXT) = CAST(:categoria AS TEXT)) " +
                    "AND (CAST(:sector as text) is null OR CAST(e.sector_id as TEXT) = CAST(:sector AS TEXT)) " +
                    "AND (CAST(:subsector as text) is null OR CAST(e.subsector_id as TEXT) = CAST(:subsector AS TEXT)) " +
                    "AND (CAST(:empresa as text) is null OR CAST(e.id as TEXT) = CAST(:empresa as text)) " +
                    // Institution filter
                    "AND (CAST(:area as text) is null OR CAST(p.area as TEXT) = CAST(:area AS TEXT)) " +
                    "GROUP BY p.id "+
                    "ORDER BY p.id DESC",
            nativeQuery = true
    )
    Page<ProjectEntity> findAllByEmpresaOrderByIdDesc(
            Pageable pageable,
            // Global specific filters
            String filtro,
            String activo,
            String fechaInicio,
            String fechaFin,
            // User|Profile filters
            String perfil,
            String usuario,
            // Module specific filters
            String prioridad,
            String estatus,
            String conResponsable,
            // Enterprise specific filters
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String empresa,
            // Institution specific filters
            String area,
            String empresaid
    );


    @Query(
            value = "SELECT DISTINCT p.* " +
                    "FROM a_proyectos AS p " +
                    "INNER JOIN a_empresas AS e ON p.empresa_id = e.id " +
                    "LEFT JOIN a_proyecto__a_proyecto_colaborador AS apapc ON p.id = apapc.proyecto_id " +
                    "LEFT JOIN a_proyectos_colaborador AS apc ON apapc.colaborador_id = apc.id " +
                    "LEFT JOIN a_usuarios AS au on apc.usuario_id = au.id " +
                    "WHERE " +
                    // Global filters
                    "(" +
                    "CAST(:filtro AS text) is null " +
                    "OR LOWER(p.folio) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.empresa) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    "OR LOWER(e.rfc) LIKE LOWER(CONCAT('%',CAST(:filtro AS text),'%')) " +
                    ") " +
                    "AND (CAST(:activo as text) is null OR LOWER(CAST(p.activo AS text)) = LOWER(CAST(:activo AS text))) " +
                    "AND (CAST(:fechaInicio as text) is null OR CAST(p.fecha_inicio as text) >= CAST(:fechaInicio as text)) " +
                    "AND (CAST(:fechaFin as text) is null OR CAST(p.fecha_fin as text) <= CAST(:fechaFin as text)) " +
                    // User|Profile filters
                    "AND (CAST(:perfil as text) is null OR (apc.usuario_id) in (" +
                    "select au2.id " +
                    "from a_usuarios as au2 " +
                    "inner join a_usuario__a_perfil AS auap on auap.usuario_id = au2.id " +
                    "inner join a_perfiles AS ap on auap.perfil_id = ap.id " +
                    "inner join (select ap2.area, ap2.nivel from a_perfiles AS ap2 where cast(ap2.id as text) = cast(:perfil as text)) as profile on profile.area = ap.area and ap.nivel>=profile.nivel" +
                    ")) " +
                    "AND (CAST(:usuario as text) is null or (p.id) in (" +
                    "select p2.id " +
                    "from a_proyectos as p2 " +
                    "inner join a_proyecto__a_proyecto_colaborador apapc2 on apapc2.proyecto_id = p2.id  " +
                    "inner join a_proyectos_colaborador apc2 on apc2.id = apapc2.colaborador_id  " +
                    "where cast(apc2.usuario_id as text) = cast(:usuario as text))" +
                    ") " +
                    // Module specific filters
                    "AND (CAST(:prioridad as text) is null OR CAST(p.prioridad as TEXT) = CAST(:prioridad AS TEXT)) " +
                    "AND (CAST(:estatus as text) is null OR CAST(p.estatus as TEXT) = CAST(:estatus AS TEXT)) " +
                    "AND (CASE " +
                    "when LOWER(CAST(:conResponsable as text)) = 'true' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') >= 1 " +
                    "when LOWER(CAST(:conResponsable as text)) = 'false' THEN (select COUNT(a_proyectos_colaborador.id) from a_proyectos_colaborador where apc.id = a_proyectos_colaborador.id AND a_proyectos_colaborador.rol = 'responsable') = 0 " +
                    "else true " +
                    "end) " +
                    //  Enterprise filters
                    "AND (CAST(:regimenFiscal as text) is null OR CAST(e.regimen_fiscal_id as TEXT) = CAST(:regimenFiscal AS TEXT)) " +
                    "AND (CAST(:categoria as text) is null OR CAST(e.categoria_id as TEXT) = CAST(:categoria AS TEXT)) " +
                    "AND (CAST(:sector as text) is null OR CAST(e.sector_id as TEXT) = CAST(:sector AS TEXT)) " +
                    "AND (CAST(:subsector as text) is null OR CAST(e.subsector_id as TEXT) = CAST(:subsector AS TEXT)) " +
                    "AND (CAST(:empresa as text) is null OR CAST(e.id as TEXT) = CAST(:empresa as text)) " +
                    // Institution filter
                    "AND (CAST(:area as text) is null OR CAST(p.area as TEXT) = CAST(:area AS TEXT)) " +
                    "AND p.tipo_id = 450 " +
                    "GROUP BY p.id "+
                    "ORDER BY p.id DESC",
            nativeQuery = true
    )
    Page<ProjectEntity> findByMesasByOrderByIdDesc(
            Pageable pageable,
            // Global specific filters
            String filtro,
            String activo,
            String fechaInicio,
            String fechaFin,
            // User|Profile filters
            String perfil,
            String usuario,
            // Module specific filters
            String prioridad,
            String estatus,
            String conResponsable,
            // Enterprise specific filters
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String empresa,
            // Institution specific filters
            String area
    );

    @Query(
            value = "select distinct p.*\n" +
                    "from a_proyectos p \n" +
                    "inner join a_proyecto__a_proyecto_colaborador pc on pc.proyecto_id = p.id \n" +
                    "inner join a_proyectos_colaborador pc2 on pc2.id = pc.colaborador_id \n" +
                    "inner join a_usuarios u on pc2.usuario_id = u.id \n" +
                    "where u.id = ?1\n" +
                    "order by p.fecha_inicio DESC",
            nativeQuery = true
    )
    List<ProjectEntity> findProjectsByUser(Long usuarioId);

    @Query(
            value = "select distinct p.*\n" +
                    "from a_proyectos p \n" +
                    "inner join a_proyecto__a_proyecto_colaborador pc on pc.proyecto_id = p.id \n" +
                    "inner join a_proyectos_colaborador pc2 on pc2.id = pc.colaborador_id \n" +
                    "inner join a_usuarios u on pc2.usuario_id = u.id \n" +
                    "order by p.fecha_inicio DESC",
            nativeQuery = true
    )
    List<ProjectEntity> findAllProjects();

    @Query(
            value = "select distinct p.*\n" +
                    "from a_proyectos p \n" +
                    "inner join a_proyecto__a_proyecto_colaborador pc on pc.proyecto_id = p.id \n" +
                    "inner join a_proyectos_colaborador pc2 on pc2.id = pc.colaborador_id \n" +
                    "inner join a_usuarios u on pc2.usuario_id = u.id \n" +
                    "Where u.id = :userId " +
                    "order by p.created_at DESC " +
                    "Limit :number",
            nativeQuery = true
    )
    List<ProjectEntity> findAllByActivoTrueOrderByCreatedAtDesc(Long number, Long userId);


    @Query(value = "SELECT sexo, count(*)  \n" +
            " FROM a_proyectos AS s\n" +
            " join a_proyectos_colaborador as pc on pc.id = s.id and pc.rol = 'interesado' " +
            " join a_usuarios as au on au.id = pc.usuario_id  \n" +
            " group by au.sexo ", nativeQuery = true)
    List<Object[]> obtenerTotalPorSexo();

    @Query(value = "    SELECT ad.municipio, count(au.id)\n" +
            " FROM a_proyectos AS s\n" +
            " join a_proyectos_colaborador as pc on pc.id = s.id and pc.rol = 'interesado' " +
            " join a_usuarios as au on au.id = pc.usuario_id  \n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresa__a_domicilio aead on aead.empresa_id = aeac.empresa_id\n" +
            "    join a_domicilios ad on ad.id =aead.domicilio_id\n" +
            "    group by ad.municipio", nativeQuery = true)
    List<Object[]> obtenerTotalPorMunicipio();

    @Query(value = "SELECT ac.nombre , count(au.id)\n" +
            " FROM a_proyectos AS s\n" +
            " join a_proyectos_colaborador as pc on pc.id = s.id and pc.rol = 'interesado' " +
            " join a_usuarios as au on au.id = pc.usuario_id  \n" +
            "    join a_empresa__a_contacto aeac on aeac.usuario_id = au.id\n" +
            "    join a_empresas ae on ae.id = aeac.empresa_id\n" +
            "    join a_catalogos ac on ac.id = ae.sector_id\n" +
            "    group by ac.nombre", nativeQuery = true)
    List<Object[]> obtenerTotalPorSector();

    List<ProjectEntity> findProyectosByTipoId_Id(Long id);

}
