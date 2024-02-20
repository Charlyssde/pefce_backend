package com.devx.software.ferias.Repositories.Enterprises;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterprisesRepository extends JpaRepository<EnterpriseEntity, Long> {

    @Query(
            value = "SELECT ae.* " +
                    "FROM a_empresas AS ae " +
                    "INNER JOIN a_empresa__a_contacto AS aeac ON aeac.empresa_id = ae.id " +
                    "INNER JOIN a_usuarios AS au ON aeac.usuario_id = au.id " +
                    "WHERE au.id = :userId " +
                    "LIMIT 1",
            nativeQuery = true
    )
    EnterpriseEntity findByUserId(Long userId);

    List<EnterpriseEntity> findAllByEstatusTrue();

    EnterpriseEntity findByRfc(String rfc);

    @Query(
            value = "SELECT e.* " +
                    "FROM a_empresas AS e " +
                    "WHERE " +
                    "(CAST(:estatus AS text) is null OR CAST(e.estatus AS text) = CAST(:estatus AS text)) " +
                    "AND (CAST(:categoria AS text) is null OR CAST(e.categoria_id AS text) = CAST(:categoria AS text)) " +
                    "AND (CAST(:regimenFiscal AS text) is null OR CAST(e.regimen_fiscal_id AS text) = CAST(:regimenFiscal AS text)) " +
                    "AND (CAST(:sector AS text) is null OR CAST(e.sector_id AS text) = CAST(:sector AS text)) " +
                    "AND (CAST(:subsector AS text) is null OR CAST(e.subsector_id AS text) = CAST(:subsector AS text)) " +
                    "ORDER BY e.empresa ASC" ,
            nativeQuery = true
    )
    List<EnterpriseEntity> getAllEnterprises(
            String estatus,
            String categoria,
            String regimenFiscal,
            String sector,
            String subsector
    );

    // Pageables
    Page<EnterpriseEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);


    @Query(
            value = "SELECT a_empresas.* FROM a_empresas " +
                    "WHERE a_empresas.id = :enterpriseId",
            nativeQuery = true
    )
    Page<EnterpriseEntity> findAllByEnterpriseId(
            Pageable pageable,
            Long enterpriseId
    );

    @Query(
            value = "SELECT a_empresas.* FROM a_empresas " +
                    "WHERE (CAST(:nombre AS text) is null OR (LOWER(a_empresas.empresa) LIKE LOWER(CONCAT('%',CAST(:nombre AS text),'%')) " +
                    "OR LOWER(a_empresas.rfc) LIKE LOWER(CONCAT('%',CAST(:nombre AS text),'%')))) " +
                    "AND (CAST(:regimenFiscal as text) is null OR CAST(a_empresas.regimen_fiscal_id as TEXT) = CAST(:regimenFiscal AS TEXT)) " +
                    "AND (CAST(:categoria as text) is null OR CAST(a_empresas.categoria_id as TEXT) = CAST(:categoria AS TEXT)) " +
                    "AND (CAST(:sector as text) is null OR CAST(a_empresas.sector_id as TEXT) = CAST(:sector AS TEXT)) " +
                    "AND (CAST(:subsector as text) is null OR CAST(a_empresas.subsector_id as TEXT) = CAST(:subsector AS TEXT)) " +
                    "AND (CAST(:estatus as text) is null OR CAST(a_empresas.estatus as TEXT) = CAST(:estatus as TEXT)) " +
                    "ORDER BY a_empresas.created_at DESC",
            nativeQuery = true
    )
    Page<EnterpriseEntity> findAllByOrderByCreatedAtDescAndMiscFilters(
            Pageable pageable,
            String nombre,
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String estatus
    );

    Page<EnterpriseEntity> findAllByAutorizadoIsTrueAndPabellonAprobadoIsTrueOrderByEmpresaDesc(Pageable pageable);

    @Query(
            value = "SELECT a_empresas.* FROM a_empresas " +
                    "WHERE (CAST(:nombre AS text) is null OR (LOWER(a_empresas.empresa) LIKE LOWER(CONCAT('%',CAST(:nombre AS text),'%')) " +
                    "OR LOWER(a_empresas.rfc) LIKE LOWER(CONCAT('%',CAST(:nombre AS text),'%')))) " +
                    "AND (CAST(:regimenFiscal as text) is null OR CAST(a_empresas.regimen_fiscal_id as TEXT) = CAST(:regimenFiscal AS TEXT)) " +
                    "AND (CAST(:categoria as text) is null OR CAST(a_empresas.categoria_id as TEXT) = CAST(:categoria AS TEXT)) " +
                    "AND (CAST(:sector as text) is null OR CAST(a_empresas.sector_id as TEXT) = CAST(:sector AS TEXT)) " +
                    "AND (CAST(:subsector as text) is null OR CAST(a_empresas.subsector_id as TEXT) = CAST(:subsector AS TEXT)) " +
                    "AND a_empresas.autorizado = true and  a_empresas.pabellon_aprobado = true " +
                    "ORDER BY a_empresas.id DESC",
            nativeQuery = true
    )
    Page<EnterpriseEntity> findAllApprovedByOrderByEmpresaDescAndMiscFilters(
            Pageable pageable,
            String nombre,
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector
    );

    @Query(value = "select e.* from a_empresas e " +
                    "inner join a_usuarios u on u.email = :correo \n",
                    nativeQuery = true)
        List<EnterpriseEntity> findEmpresaPorCorreo(@Param("correo") String correo);

    List<EnterpriseEntity> findAllByEstatus(String estatus);


    @Query(value = "SELECT sexo, count(*)  \n" +
            "FROM a_empresas AS s\n" +
            "    join a_empresa__a_contacto aeac on aeac.empresa_id = s.id\n" +
            " join a_usuarios as au on au.id = aeac.usuario_id  \n" +
            " group by au.sexo ", nativeQuery = true)
    List<Object[]> obtenerTotalPorSexo();

    @Query(value = "    SELECT ad.municipio, count(s.id)\n" +
            "    FROM a_empresas AS s\n" +
            "    join a_empresa__a_contacto aeac on aeac.empresa_id = s.id\n" +
            "    join a_empresa__a_domicilio aead on aead.empresa_id = aeac.empresa_id\n" +
            "    join a_domicilios ad on ad.id =aead.domicilio_id\n" +
            "    group by ad.municipio", nativeQuery = true)
    List<Object[]> obtenerTotalPorMunicipio();

    @Query(value = "SELECT ac.nombre , count(s.id)\n" +
            "    FROM a_solicitudes AS s\n" +
            "    join a_empresa__a_contacto aeac on aeac.empresa_id = s.id\n" +
            "    join a_empresas ae on ae.id = aeac.empresa_id\n" +
            "    join a_catalogos ac on ac.id = ae.sector_id\n" +
            "    group by ac.nombre", nativeQuery = true)
    List<Object[]> obtenerTotalPorSector();
}
