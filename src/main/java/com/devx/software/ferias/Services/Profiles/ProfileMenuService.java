package com.devx.software.ferias.Services.Profiles;

import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import com.devx.software.ferias.Repositories.Profiles.ProfileMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileMenuService {
    private final ProfileMenuRepository profileMenuRepository;

    @Autowired
    public ProfileMenuService(
            ProfileMenuRepository profileMenuRepository
    ) {
        this.profileMenuRepository = profileMenuRepository;
    }


    public ProfileMenuEntity create(ProfileMenuEntity entity) throws Exception {
        try {
            return this.profileMenuRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ProfileMenuEntity> page() {
        return this.profileMenuRepository.findAll();
    }

    public List<ProfileMenuEntity> findByPerfilId(Long id) {
        return this.profileMenuRepository.findAllByPerfilId(id);
    }

    public ProfileMenuEntity findByPerfilIdAndMenuId(Long perfilId, Long menuId) {
        return this.profileMenuRepository.findPermisosPerfilesByPerfilIdAndMenuId(perfilId, menuId);
    }

    public ProfileMenuEntity update(ProfileMenuEntity entity) {

        return this.profileMenuRepository.save(entity);
    }

    public void delete(Long perfilId, Long menuId) {
        ProfileMenuEntity toDelete = this.profileMenuRepository.findPermisosPerfilesByPerfilIdAndMenuId(perfilId, menuId);
        this.profileMenuRepository.delete(toDelete);
    }
}
