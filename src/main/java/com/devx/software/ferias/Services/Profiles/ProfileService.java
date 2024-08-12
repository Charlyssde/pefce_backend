package com.devx.software.ferias.Services.Profiles;

import com.devx.software.ferias.DTOs.Profiles.OrganizationChartDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileListFilterDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuKey;
import com.devx.software.ferias.Mappers.Profile.ProfileListFilterMapper;
import com.devx.software.ferias.Repositories.Profiles.ProfileMenuRepository;
import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMenuRepository profileMenuRepository;

    private final ProfileListFilterMapper profileListFilterMapper;

    @Autowired
    public ProfileService(
            ProfileRepository profileRepository,
            ProfileMenuRepository profileMenuRepository,

            ProfileListFilterMapper profileListFilterMapper
    ) {
        this.profileRepository = profileRepository;
        this.profileMenuRepository = profileMenuRepository;

        this.profileListFilterMapper = profileListFilterMapper;
    }


    // Profiles
    public ProfileEntity findProfileById(Long profileId) throws Exception {
        ProfileEntity profileEntity = this.profileRepository.findById(profileId).get();
        if (profileEntity != null) {
            return profileEntity;
        }
        throw new Exception("El perfil no existe");
    }

    public List<ProfileListFilterDTO> findByAreaAndLevel(String area, Long level) throws Exception {
        if(area != null){
            List<ProfileEntity> profileEntityList = new ArrayList<>();
            profileEntityList = this.profileRepository.findChildProfilesByNivelAndArea(level,area);
            return this.profileListFilterMapper.listEntityToDTO(profileEntityList);
        }
        throw new Exception("No se ha seleccionado un área");
    }

    public List<ProfileEntity> getAllProfiles() {
        return this.profileRepository.findAll();
    }

    public Page<ProfileEntity> pageProfiles(Pageable pageable, String nombre) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);

        if (activeProfileEntity != null) {
            if (activeProfileEntity.getTipo().equals("root") && activeProfileEntity.getNombre().equals("Superadministrador")) {
                if (nombre == null) {
                    return this.profileRepository.findAllByOrderByIdAsc(pageable);
                }
                return this.profileRepository.getByNombreContainingIgnoreCaseOrTipoContainingIgnoreCase(nombre, pageable);
            }
            if (activeProfileEntity.getTipo().equals("root") && activeProfileEntity.getNombre().equals("Administrador")) {
                if (nombre == null) {
                    return this.profileRepository.getProfilesByAdministratorProfile(pageable);
                }
                return this.profileRepository.getProfilesByAdministratorProfileAndNombreContains(nombre, pageable);
            }
            if (activeProfileEntity.getTipo().equals("institución")) {
                Long nivel = activeProfileEntity.getNivel();
                String area = activeProfileEntity.getArea();
                if (nivel == 1) {
                    if (nombre == null) {
                        return this.profileRepository.getProfilesByInstitutionAndLevelOne(pageable);
                    }
                    return this.profileRepository.getProfilesByInstitutionAndLevelOneAndNombreContains(nombre, pageable);
                }
                if (nivel > 1) {
                    if (nombre == null) {
                        return this.profileRepository.getProfilesByInstitutionAndLevelPlusOne(nivel, area, pageable);
                    }
                    return this.profileRepository.getProfilesByInstitutionAndLevelPlusOneAndNombreContains(nivel, area, nombre, pageable);
                }
            }
            throw new Exception("El usuario no tiene acceso a los recursos");
        }
        throw new Exception("El usuario no tiene una sesión iniciada");
    }

    private OrganizationChartDTO iterateChild(List<ProfileEntity> profilesEntity, OrganizationChartDTO parent) {
        for (ProfileEntity profile : profilesEntity) {
            if (parent.getTitle().equals("")) {
                parent.setTitle(profile.getNombre());
                this.iterateChild(profile.getPerfiles(), parent);
            } else {
                if (profile.getNivel() != null && profile.getEstatus()) {
                    OrganizationChartDTO child = new OrganizationChartDTO(profile.getNombre(), new ArrayList<>());
                    if (profile.getPerfiles().size() > 0) {
                        this.iterateChild(profile.getPerfiles(), child);
                    }
                    parent.getChilds().add(child);
                }
            }
        }
        return parent;
    }

    public OrganizationChartDTO getOrganizationChart() {
        List<ProfileEntity> profiles = this.profileRepository.findProfileEntityByTipoAndNivelOrderByNivelAsc("institución", 1L);
        OrganizationChartDTO parent = new OrganizationChartDTO("", new ArrayList<>());
        return this.iterateChild(profiles, parent);
    }

    public List<ProfileEntity> getAllInstitutionalProfiles() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);
        if (activeProfileEntity != null) {
            if (activeProfileEntity.getTipo().equals("root") || (activeProfileEntity.getTipo().equals("institución") && activeProfileEntity.getNivel() == 1)) {
                return this.profileRepository.findProfileEntityByTipoOrderByNombreAsc("institución");
            }
            if (activeProfileEntity.getTipo().equals("institución") && activeProfileEntity.getNivel() > 1) {
                return this.profileRepository.findChildProfilesByNivelAndArea(activeProfileEntity.getNivel(), activeProfileEntity.getArea());
            }
        }
        throw new Exception("El usuario no tiene una sesión iniciada");
    }

    public ProfileEntity save(ProfileEntity profileEntity) {
        return this.profileRepository.save(profileEntity);
    }

    public ProfileEntity updateStatusProfile(Long profileId, Boolean status) throws Exception {
        ProfileEntity profileEntity = this.profileRepository.findById(profileId).get();
        if (profileEntity != null) {
            profileEntity.setEstatus(status);
            return this.profileRepository.save(profileEntity);
        }
        throw new Exception("El perfil no existe");
    }

    public HashMap<String, Object> deleteProfile(Long profileId) throws Exception {
        ProfileEntity profileEntity = this.findProfileById(profileId);
        if (profileEntity != null) {
            this.profileRepository.deleteById(profileId);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Perfil eliminado");
            return response;
        }
        throw new Exception("El perfil no existe");
    }


    // Profile permissions
    public void updatePermissionsByProfile(List<ProfileMenuEntity> permissions, ProfileEntity profileEntity) throws Exception {
        // Delete existing profiles
        this.profileMenuRepository.deleteByPerfil_Id(profileEntity.getId());
        // Insert new permissions
        if (permissions != null) {
            for (ProfileMenuEntity profileMenuEntity : permissions) {
                if (profileMenuEntity.getPerfil() == null) {
                    profileMenuEntity.setPerfil(profileEntity);
                }
                ProfileMenuKey profileMenuKey = new ProfileMenuKey(profileEntity.getId(), profileMenuEntity.getMenu().getId());
                ProfileMenuEntity permission = new ProfileMenuEntity(
                        profileMenuKey,
                        profileMenuEntity.getCrear(),
                        profileMenuEntity.getLeer(),
                        profileMenuEntity.getActualizar(),
                        profileMenuEntity.getEliminar(),
                        profileMenuEntity.getReportar()
                );
                permission.setPerfil(profileEntity);
                permission.setMenu(profileMenuEntity.getMenu());
                this.profileMenuRepository.save(permission);
            }
        }
    }

    public List<ProfileMenuEntity> getPermissionsByProfileId(Long profileId) throws Exception {
        return this.profileMenuRepository.findAllByPerfilId(profileId);
    }

    public List<ProfileEntity> getAllByInstitution() throws Exception {
        return this.profileRepository.getByInstitution();
    }
}
