/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Repositories.Users;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Entities.Users.UserProfileKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UserProfileKey> {
    List<UserProfileEntity> findPermisosPerfilesByUsuarioId(Long id);

    UserProfileEntity findPermisosPerfilesByPerfilIdAndUsuarioId(Long idPerfil, Long idUsuario);

    List<UserProfileEntity> findAllByPerfilId(Long id);

}
