package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Auth.PasswordRecoveryRequest;
import com.devx.software.ferias.DTOs.Auth.PasswordRecoveryResponse;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.DTOs.Users.*;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Mappers.Profile.ProfileMapper;
import com.devx.software.ferias.Mappers.Users.FormResourcesUserMapper;
import com.devx.software.ferias.Mappers.Users.UserListFilterMapper;
import com.devx.software.ferias.Mappers.Users.UserListMapper;
import com.devx.software.ferias.Mappers.Users.UserProfileListMapper;
import com.devx.software.ferias.Services.Profiles.ProfileService;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Services.Users.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UsersController {

    private final ProfileService profileService;
    private final UserService userService;
    private final UserListMapper userListMapper;
    private final UserProfileListMapper userProfileListMapper;
    private final FormResourcesUserMapper formResourcesUserMapper;
    private final UserProfileService userProfileService;
    private final UserListFilterMapper userListFilterMapper;
    private final ProfileMapper profileMapper;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public UsersController(
            UserService userService,
            ProfileService profileService,
            UserListMapper userListMapper,
            UserProfileListMapper userProfileListMapper,
            FormResourcesUserMapper formResourcesUserMapper,
            UserProfileService userProfileService,
            UserListFilterMapper userListFilterMapper,
            ProfileMapper profileMapper
    ) {
        this.userService = userService;
        this.profileService = profileService;
        this.userListMapper = userListMapper;
        this.userProfileListMapper = userProfileListMapper;
        this.formResourcesUserMapper = formResourcesUserMapper;
        this.userProfileService = userProfileService;
        this.userListFilterMapper = userListFilterMapper;
        this.profileMapper = profileMapper;
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<UserListDTO>> getAllUsers() {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.userListMapper.listEntityToDto(this.userService.getAllUsers()), this.headers, HttpStatus.OK);
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
            Page<UserEntity> pageDataset = this.userService.pageUsers(pegeable, nombre);
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.userListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> findUserById(
            @PathVariable Long userId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.userService.findUserById(userId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{profileId}/profile")
    public ResponseEntity<List<UserListDTO>> getUsersByProfileId(
            @PathVariable Long profileId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.userListMapper.listEntityToDto(this.userService.getUsersByProfileId(profileId)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filterByInstitutionProfile")
    public ResponseEntity<List<UserListFilterDTO>> filterByInstitutionProfile(){
        try{
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity<>(this.userListFilterMapper.listEntityToDTO(this.userService.getAllUsersWhereProfileIsInstitution()), this.headers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllUsersWhereProfileIsInstitution")
    public ResponseEntity<List<UserListDTO>> findAllUsersWhereProfileIsInstitution(){
        try{
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity<>(this.userListMapper.listEntityToDto(this.userService.getAllUsersWhereProfileIsInstitution()), this.headers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/formResources")
    public ResponseEntity<HashMap<String, Object>> formResources(
            @PathVariable(required = false) Long userId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            FormResourcesUserDTO user = null;
            if (userId > 0) {
                user = this.formResourcesUserMapper.entityToDTO(this.userService.findUserById(userId));
            }
            formResources.put("user", user);
            formResources.put("profilesList", this.profileMapper.listEntityToformResourcesProfileDTO(this.userService.getAllInstitutionalProfiles()));
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            this.headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(this.userService.saveUser(userRequestDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.userService.updateUser(userRequestDTO.getUser().getId(), userRequestDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HashMap<String, Object>> deleteUser(@PathVariable Long userId) {
        try {
            this.headers.set("200", "Borrado exitoso");
            return new ResponseEntity<>(this.userService.deleteUser(userId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{userId}/status/{status}")
    public ResponseEntity<UserListDTO> updateStatusUser(@PathVariable Long userId, @PathVariable Boolean status) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.userListMapper.entityToDto(this.userService.updateStatusUser(userId, status)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/password-recovery")
    public ResponseEntity<PasswordRecoveryResponse> passwordRecovery(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest) {
        try {
            this.userService.passwordRecovery(passwordRecoveryRequest);
            this.headers.set("200", "Recuperar contraseña exitoso");
            PasswordRecoveryResponse passwordRecoveryResponse = new PasswordRecoveryResponse();
            passwordRecoveryResponse.setMessage("Se ha enviado una nueva contraseña al email " + passwordRecoveryRequest.getEmail());
            return new ResponseEntity<>(passwordRecoveryResponse, this.headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageByPerfilUsuarioId/{id}")
    public ResponseEntity<List<UserEntity>> pageByPerfilUsuario(@PathVariable Long id) {
        List<UserProfileEntity> getListData;
        HttpHeaders headers = new HttpHeaders();
        try {
            if( id > 0 ) {
                getListData = userProfileService.findAllByPerfilUsuarioId(id);
            }else{
                getListData = userProfileService.findAll();
            }
            List<UserEntity> responseData = null;
            if( !getListData.isEmpty() ){
                responseData = new ArrayList<>();
                for ( UserProfileEntity res : getListData ){
                    responseData.add( res.getUsuario() );
                }
            }
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // SCOPES
    @GetMapping("/getAllActiveUsersByProfileArea")
    public ResponseEntity<List<UserInstitutionSelectDTO>> getAllActiveUsersByProfileArea(
            @RequestParam(required = false) String area
    )  {
        try{
            this.headers.add("200","Consulta exitosa");
            return new ResponseEntity<>(
                    this.userService.getAllActiveUsersByProfileArea(area),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

/*

    @PostMapping("/create")
    public ResponseEntity<UsuariosEntity> create(@RequestBody UsuariosDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.create(dto);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUsuarioContenido")
    public ResponseEntity<UsuariosEntity> createUsuarioContenido(@RequestBody UsuariosDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.create(dto);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UsuariosEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.findById(id);
            if (responseData != null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<UsuariosEntity> findByEmail(@PathVariable String email) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.findByEmail(email);
            if (responseData != null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<UsuariosPageDTO>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UsuariosEntity> getListData = usuariosService.page();
            List<UsuariosPageDTO> responseData = !getListData.isEmpty() ? usuariosMapper.listEntityToPageDTO(getListData) : new ArrayList<>();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageByTipoUsuario/{tipoUsuario}")
    public ResponseEntity<List<UsuariosPageDTO>> pageByTipoUsuario(@PathVariable String tipoUsuario) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UsuariosEntity> getListData = usuariosService.pageByTipoUsuario(tipoUsuario);
            List<UsuariosPageDTO> responseData = !getListData.isEmpty() ? usuariosMapper.listEntityToPageDTO(getListData) : new ArrayList<>();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageByPerfilUsuarioId/{id}")
    public ResponseEntity<List<UsuariosPageDTO>> pageByPerfilUsuario(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {

            List<UsuariosEntity> getListData = usuariosService.findAllByPerfilUsuarioId(id);
            List<UsuariosPageDTO> responseData = !getListData.isEmpty() ? usuariosMapper.listEntityToPageDTO(getListData) : new ArrayList<>();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pageByIdDirectorio/{id}")
    public ResponseEntity<List<UsuariosPageDTO>> pageByIdDirectorio(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UsuariosEntity> getListData = usuariosService.pageByTipoIdDirectorio(id);
            List<UsuariosPageDTO> responseData = !getListData.isEmpty() ? usuariosMapper.listEntityToPageDTO(getListData) : new ArrayList<>();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UsuariosEntity> update(@RequestBody UsuariosEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<UsuariosEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            UsuariosEntity responseData = usuariosService.findById(id);
            if (responseData != null) {
                usuariosService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
 */
