package com.devx.software.ferias.Repositories.Profiles;

import com.devx.software.ferias.Entities.Profiles.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenusRepository extends JpaRepository<MenuEntity, Long> {

    List<MenuEntity> findAllByPadreNullOrderByNombreAsc();

    MenuEntity findMenuById(Long id);

    @Query(value = "SELECT * FROM menus ms WHERE MS.id_padre IS NULL AND ms.id NOT IN (SELECT pp.id_menu FROM permisos_perfiles pp WHERE pp.id_perfil = ?1)", nativeQuery = true)
    List<MenuEntity> findMenusNoRegistrados(Long idPerfil);

    @Query(value = "SELECT ms.* FROM menus ms INNER JOIN permisos_perfiles pp ON ms.id = pp.id_menu WHERE pp.id_perfil = ?1 AND ms.id NOT IN (SELECT pp.id_menu FROM permisos_perfiles pp WHERE pp.id_perfil = ?2)", nativeQuery = true)
    List<MenuEntity> findMenusNoRegistrados(Long idPerfilPadre, Long idPerfil);
}
