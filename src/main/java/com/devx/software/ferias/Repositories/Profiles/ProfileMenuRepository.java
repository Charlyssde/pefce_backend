package com.devx.software.ferias.Repositories.Profiles;

import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProfileMenuRepository extends JpaRepository<ProfileMenuEntity, ProfileMenuKey> {
    @Transactional
    void deleteByPerfil_Id(Long profileId);

    List<ProfileMenuEntity> findAllByPerfilId(Long id);

    ProfileMenuEntity findPermisosPerfilesByPerfilIdAndMenuId(Long idPerfil, Long idMenu);
}
