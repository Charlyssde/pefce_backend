package com.devx.software.ferias.Services.Users;

import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Repositories.Users.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileEntity create(UserProfileEntity entity) throws Exception {
        try {
            return userProfileRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UserProfileEntity> page() {
        return userProfileRepository.findAll();
    }

    public List<UserProfileEntity> findAllByUsuarioId(Long id) {
        return userProfileRepository.findPermisosPerfilesByUsuarioId(id);
    }

    public List<UserProfileEntity> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfileEntity findByPerfilIdAndUsuarioId(Long idPefil, Long idUsuario) {
        return this.userProfileRepository.findPermisosPerfilesByPerfilIdAndUsuarioId(idPefil, idUsuario);
    }

    public UserProfileEntity update(UserProfileEntity entity) {
        return userProfileRepository.save(entity);
    }

    public void delete(Long idPerfil, Long idUsuario) {
        UserProfileEntity toDelete = this.userProfileRepository.findPermisosPerfilesByPerfilIdAndUsuarioId(idPerfil, idUsuario);
        this.userProfileRepository.delete(toDelete);
    }

    public List<UserProfileEntity> findAllByPerfilUsuarioId(Long id) {
        return userProfileRepository.findAllByPerfilId(id);
    }
}
