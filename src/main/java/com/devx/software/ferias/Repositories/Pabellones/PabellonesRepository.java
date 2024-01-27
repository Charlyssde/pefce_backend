package com.devx.software.ferias.Repositories.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PabellonesRepository extends JpaRepository<PabellonesEntity, Long> {

    PabellonesEntity findPabellonesById(Long id);

    @Query(value = "select p.* \n" +
            "from pabellones p \n" +
            "inner join directorio_empresarial de on p.id_directorio_empresarial = de.id \n" +
            "where de.id = :idDirectorioEmpresarial",
            nativeQuery = true)
    PabellonesEntity findByIdDirectorioEmpresarial(@Param("idDirectorioEmpresarial") Long idDirectorioEmpresarial);

    @Query(value = "select \n" +
            "	p.*\n " +
            "from usuario_empresa ue\n" +
            "inner join directorio_empresarial de on ue.id_directorio_empresarial = de.id \n" +
            "inner join pabellones p on de.id = p.id_directorio_empresarial \n" +
            "where ue.id_usuario = :idUsuario " +
            "Limit 1",
            nativeQuery = true)
    PabellonesEntity findFirstPabellonByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query(value = "select \n" +
            "	p.*" +
            "from usuario_empresa ue\n" +
            "inner join directorio_empresarial de on ue.id_directorio_empresarial = de.id \n" +
            "inner join pabellones p on de.id = p.id_directorio_empresarial \n" +
            "where p.id = :id "
            + "limit 1",
            nativeQuery = true)
    PabellonesEntity findPabellonById(@Param("id") Long id);


    @Query(value = "select \n" +
            "	p.*\n" +
            "from evento_empresa ee \n" +
            "join directorio_empresarial de on ee.id_directorio_empresarial = de.id \n" +
            "join pabellones p on p.id_directorio_empresarial = de.id \n" +
            "where ee.id_evento = :idEvento \n" +
            "and ee.estatus = 'aceptada' \n" +
            "and p.estatus = true",
            nativeQuery = true)
    List<PabellonesEntity> getPabellonesByIdEvento(@Param("idEvento") Long idEvento);


    @Query(value = "select p.* from pabellones p where p.estatus = true", nativeQuery = true)
    List<PabellonesEntity> getPabellonesActive();

}
