package com.devx.software.ferias.Repositories.Users;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "SELECT DISTINCT usuarios.* FROM a_usuarios as usuarios " +
                    "INNER JOIN a_usuario__a_perfil as usuario_perfil ON usuarios.id = usuario_perfil.usuario_id " +
                    "WHERE usuario_perfil.perfil_id = :perfilId " +
                    "GROUP BY usuarios.id",
            nativeQuery = true
    )
    List<UserEntity> getAllUsersByProfileId(Long perfilId);






    // List usuarios
    UserEntity findUserById(Long id);

    List<UserEntity> findUserEntitiesByOrderByIdDesc();

    @Query(
            value = "SELECT u.* " +
                    "FROM a_usuarios as u " +
                    "INNER JOIN a_usuario__a_perfil as auap ON auap.usuario_id = u.id " +
                    "INNER JOIN a_perfiles as ap ON auap.perfil_id = ap.id " +
                    "WHERE " +
                    "(CAST(:area as text) is null OR CAST(ap.area as text) = CAST(:area as text)) " +
                    "AND ap.tipo = 'institución'" +
                    "AND u.estatus = true " +
                    "GROUP BY u.id " +
                    "ORDER BY u.nombre ASC ",
            nativeQuery = true
    )
    List<UserEntity> getAllActiveUsersByProfileArea(String area);

    // Get all institution users
    @Query(
            value = "SELECT usuarios.* FROM a_usuarios as usuarios " +
                    "INNER JOIN a_usuario__a_perfil as usuario_perfil ON usuarios.id = usuario_perfil.usuario_id " +
                    "INNER JOIN a_perfiles as perfiles ON perfiles.id = usuario_perfil.perfil_id " +
                    "WHERE perfiles.tipo = 'institución'",
            nativeQuery = true
    )
    List<UserEntity> getAllUsersWhereProfileIsInstitution();

    // Page usuarios root - superadministrador
    Page<UserEntity> findUserEntitiesByOrderByIdDesc(Pageable pageable);

    Page<UserEntity> findUserEntitiesByNombreContainingIgnoreCaseOrderByIdDesc(Pageable pageable, String nombre);

    // Page usuarios root - administrador
    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.nombre NOT IN ('Superadministrador','Administrador','Empresa') " +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByAdministratorProfile(Pageable pageable);

    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.nombre NOT IN ('Superadministrador','Administrador','Empresa') " +
                    "AND LOWER(a_usuarios.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))" +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByAdministratorProfileAndNombreContains(Pageable pageable, String nombre);

    // Page usuarios institución - nivel 1
    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.tipo = 'institución' " +
                    "AND a_perfiles.nivel > 1 " +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByInstitutionProfileLevelOne(Pageable pageable);

    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.tipo = 'institución' " +
                    "AND a_perfiles.nivel > 1 " +
                    "AND LOWER(a_usuarios.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))" +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByInstitutionProfileLevelOneAndNombreContains(Pageable pageable, String nombre);

    // Page usuarios institución - nivel > 1
    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.tipo = 'institución' " +
                    "AND a_perfiles.nivel > :nivel " +
                    "and a_perfiles.area = :area " +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByInstitutionProfileLevelPlusOne(Pageable pageable, Long nivel, String area);

    @Query(
            value = "SELECT a_usuarios.* FROM a_usuarios " +
                    "INNER JOIN a_usuario__a_perfil ON a_usuarios.id = a_usuario__a_perfil.usuario_id " +
                    "INNER JOIN a_perfiles ON a_usuario__a_perfil.perfil_id = a_perfiles.id " +
                    "WHERE a_perfiles.tipo = 'institución' " +
                    "AND a_perfiles.nivel > :nivel " +
                    "and a_perfiles.area = :area " +
                    "AND LOWER(a_usuarios.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))" +
                    "ORDER BY a_usuarios.id DESC",
            nativeQuery = true
    )
    Page<UserEntity> findUserEntitiesByInstitutionProfileLevelPlusOneAndNombreContains(Pageable pageable, Long nivel, String area, String nombre);


    @Query(value = "SELECT usuario.* FROM a_usuarios as usuario WHERE usuario.email = ?1 AND usuario.estatus = true", nativeQuery = true)
    UserEntity userAuthentication(String email);


    // DELETE
    @Transactional
    void deleteAllByIdIn(List<Long> contactIds);

    //List<UserEntity> findAllByTipoUsuario(String tipoUsuario);
    
    @Query(value = "SELECT au.* \n"
            + "FROM a_usuarios au \n"
            + "JOIN a_minuta__a_usuario amau ON au.id = amau.usuario_id\n"
            + "JOIN a_minutas am ON amau.minuta_id = am.id\n"
            + "WHERE am.id = ?1 ", nativeQuery = true)
    List<UserEntity> findUsuariosByMinutaId(Long minutaId);

}
