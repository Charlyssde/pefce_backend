package com.devx.software.ferias.Repositories.Profiles;

import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    @Transactional
    void deleteById(Long profileId);

    List<ProfileEntity> findProfileEntityByTipoAndNivelOrderByNivelAsc(String tipo, Long nivel);

    List<ProfileEntity> findAllByOrderByIdAsc(Sort sort);

    List<ProfileEntity> findProfileEntityByTipoOrderByNivelAsc(String tipo, Sort sort);

    List<ProfileEntity> findProfileEntityByTipoOrderByNombreAsc(String tipo);

    @Query(
            value = "SELECT a_perfiles.* " +
                    "FROM a_perfiles " +
                    "WHERE a_perfiles.tipo = 'institución' " +
                    "AND a_perfiles.nivel > :nivel " +
                    "AND a_perfiles.area = :area " +
                    "ORDER BY a_perfiles.nivel ASC, a_perfiles.area ASC",
            nativeQuery = true
    )
    List<ProfileEntity> findChildProfilesByNivelAndArea(Long nivel, String area);

    @Query(
            value = "SELECT a_perfiles.* " +
                    "FROM a_perfiles " +
                    "inner join a_sesiones on a_sesiones.perfil_id = a_perfiles.id " +
                    "inner join a_usuarios on a_sesiones.usuario_id = a_usuarios.id " +
                    "WHERE a_sesiones.estatus = true " +
                    "AND a_usuarios.email = :email " +
                    "AND a_perfiles.nivel > :nivel " +
                    "AND a_perfiles.area = :area " +
                    "order by a_perfiles.nivel ASC, a_perfiles.area ASC",
            nativeQuery = true
    )
    List<ProfileEntity> findAllProfileEntityByUserActiveSessionEmail(String email, Long nivel, String area);

    ProfileEntity findProfileEntityByTipo(String tipo);

    // Page root - Superadministrador
    Page<ProfileEntity> findAllByOrderByIdAsc(Pageable pageable);

    @Query(
            "SELECT profile FROM ProfileEntity as profile " +
                    "WHERE LOWER(profile.nombre) LIKE LOWER(CONCAT('%',:nombre,'%')) " +
                    "OR LOWER(profile.tipo) LIKE LOWER(CONCAT('%',:nombre,'%')) " +
                    "ORDER BY profile.id ASC"
    )
    Page<ProfileEntity> getByNombreContainingIgnoreCaseOrTipoContainingIgnoreCase(String nombre, Pageable pageable);

    // Page root - Administrador
    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.nombre NOT IN ('Superadministrador','Empresa') " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByAdministratorProfile(Pageable pageable);

    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.nombre NOT IN ('Superadministrador','Empresa') " +
                    "AND LOWER(profile.nombre) LIKE LOWER(CONCAT('%',?1,'%')) " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByAdministratorProfileAndNombreContains(String nombre, Pageable pageable);

    // Page institución - nivel 1
    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.tipo = 'institución' " +
                    "AND profile.nivel > 1 " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByInstitutionAndLevelOne(Pageable pageable);

    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.tipo = 'institución' " +
                    "AND profile.nivel > 1 " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    List<ProfileEntity> getByInstitution();

    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.tipo = 'institución' " +
                    "AND profile.nivel > 1 " +
                    "AND LOWER(profile.nombre) LIKE LOWER(CONCAT('%',?1,'%')) " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByInstitutionAndLevelOneAndNombreContains(String nombre, Pageable pageable);

    // Page institución - nivel > 1
    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.tipo = 'institución' " +
                    "AND profile.nivel > ?1 " +
                    "AND profile.area = ?2 " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByInstitutionAndLevelPlusOne(Long nivel, String area, Pageable pageable);

    @Query(
            "SELECT profile " +
                    "FROM ProfileEntity as profile " +
                    "WHERE profile.tipo = 'institución' " +
                    "AND profile.nivel > ?1 " +
                    "AND profile.area = ?2 " +
                    "AND LOWER(profile.nombre) LIKE LOWER(CONCAT('%',?3,'%')) " +
                    "ORDER BY profile.nivel ASC, profile.area ASC"
    )
    Page<ProfileEntity> getProfilesByInstitutionAndLevelPlusOneAndNombreContains(Long nivel, String area, String nombre, Pageable pageable);


    @Query(
            value = "SELECT a_perfiles.* " +
                    "FROM a_perfiles " +
                    "inner join a_sesiones on a_sesiones.perfil_id = a_perfiles.id " +
                    "inner join a_usuarios on a_sesiones.usuario_id = a_usuarios.id " +
                    "WHERE a_usuarios.email = :email " +
                    "and a_sesiones.estatus = true",
            nativeQuery = true
    )
    ProfileEntity findByUserActiveSessionEmail(String email);


}
