package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Profiles.FormResourcesProfileDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileListFilterDTO;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Mappers.Profile.ProfileListMapper;
import com.devx.software.ferias.Mappers.Profile.ProfileMapper;
import com.devx.software.ferias.Services.Profiles.MenusService;
import com.devx.software.ferias.Services.Profiles.ProfileService;
import com.devx.software.ferias.Services.Users.UserProfileService;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/profiles")
@PreAuthorize("isAuthenticated()")
public class ProfilesController {

    private final HttpHeaders headers = new HttpHeaders();
    private final MenusService menusService;
    private final ProfileMapper profileMapper;
    private final ProfileListMapper profileListMapper;
    private final ProfileService profileService;
    private final UserService userService;
    private final UserProfileService userProfileService;

    @Autowired
    public ProfilesController(
            MenusService menusService,
            ProfileMapper profileMapper,
            ProfileListMapper profileListMapper,
            ProfileService profileService,
            UserService userService,
            UserProfileService userProfileService
    ) {
        this.menusService = menusService;
        this.profileMapper = profileMapper;
        this.profileListMapper = profileListMapper;
        this.profileService = profileService;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/filterByArea/{area}/level/{level}")
    public ResponseEntity<ProfileListFilterDTO> findByArea(
            @PathVariable String area,
            @PathVariable Long level
    ){
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity(
                    this.profileService.findByAreaAndLevel(area,level), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileEntity> findById(@PathVariable Long profileId) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.profileService.findProfileById(profileId), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/organizationChart")
    public ResponseEntity<?> getOrganizationChart() {
        try {
            return ResponseEntity.ok(this.profileService.getOrganizationChart());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<ProfileEntity>> getAllProfiles() throws Exception {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.profileService.getAllProfiles(), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> pageProfiles(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pegeable = PageRequest.of(page, size);
            Page<ProfileEntity> pageDataset = this.profileService.pageProfiles(pegeable, nombre);
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.profileListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{profileId}/formResources")
    public ResponseEntity<HashMap<String, Object>> formResources(
            @PathVariable(required = false) Long profileId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            FormResourcesProfileDTO profile = null;
            List<ProfileMenuEntity> profilesMenus = null;
            if (profileId > 0) {
                profile = this.profileMapper.entityToformResourcesProfileDTO(this.profileService.findProfileById(profileId));
                profilesMenus = this.profileService.getPermissionsByProfileId(profileId);
                profile.setPermisos(profilesMenus);
            }
            formResources.put("profile", profile);
            formResources.put("profilesList", this.profileMapper.listEntityToformResourcesProfileDTO(this.profileService.getAllInstitutionalProfiles()));
            formResources.put("menuList", this.menusService.getMainMenus());
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{profileId}/permissions")
    public ResponseEntity<List<ProfileMenuEntity>> getProfileMenuPermissionsByProfileId(
            @PathVariable(required = false) Long profileId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.profileService.getPermissionsByProfileId(profileId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<FormResourcesProfileDTO> createProfile(
            @RequestBody FormResourcesProfileDTO formResourcesProfileDTO
    ) {
        try {
            ProfileEntity profile = this.profileService.save(this.profileMapper.formResourcesProfileDtoToEntity(formResourcesProfileDTO));
            this.profileService.updatePermissionsByProfile(formResourcesProfileDTO.getPermisos(), profile);
            this.headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(this.profileMapper.entityToformResourcesProfileDTO(profile), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<FormResourcesProfileDTO> updateProfile(
            @PathVariable Long profileId,
            @RequestBody FormResourcesProfileDTO formResourcesProfileDTO
    ) {
        try {
            ProfileEntity profile = this.profileService.save(this.profileMapper.formResourcesProfileDtoToEntity(formResourcesProfileDTO));
            this.profileService.updatePermissionsByProfile(formResourcesProfileDTO.getPermisos(), profile);
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.profileMapper.entityToformResourcesProfileDTO(profile), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<HashMap<String, Object>> deleteProfile(@PathVariable Long profileId) {
        try {
            this.headers.set("200", "Borrado exitoso");
            return new ResponseEntity<>(this.profileService.deleteProfile(profileId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{profileId}/status/{status}")
    public ResponseEntity<ProfileEntity> updateStatusProfile(@PathVariable Long profileId, @PathVariable Boolean status) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.profileService.updateStatusProfile(profileId, status), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // User permissions
    @PostMapping("/grant/{userId}")
    public ResponseEntity<List<UserProfileEntity>> grentPermissions(
            @PathVariable long userId,
            @RequestBody List<ProfileEntity> profilesEntity
    ) throws Exception {
        this.headers.set("200", "Registro exitoso");
        try {
            UserEntity user = this.userService.findUserById(userId);
            for (ProfileEntity profile : profilesEntity) {
                UserProfileEntity userProfileEntity = new UserProfileEntity();
                userProfileEntity.setUsuario(user);
                userProfileEntity.setPerfil(profile);
                this.userProfileService.create(userProfileEntity);
                return new ResponseEntity<>(this.userProfileService.findAllByUsuarioId(userId), headers, HttpStatus.OK);
            }
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(this.userProfileService.findAllByUsuarioId(userId), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
