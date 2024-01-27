package com.devx.software.ferias.Services.Auth;

import com.devx.software.ferias.DTOs.Auth.*;
import com.devx.software.ferias.DTOs.Profiles.ChildPermissionDTO;
import com.devx.software.ferias.DTOs.Profiles.PermissionDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileAuthDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Logs.SessionEntity;
import com.devx.software.ferias.Entities.Profiles.MenuEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import com.devx.software.ferias.Entities.Shared.DomicilioEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Mappers.Auth.AuthMapper;
import com.devx.software.ferias.Mappers.Profile.ProfileAuthMapper;
import com.devx.software.ferias.Mappers.Profile.ProfileMapper;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Repositories.Logs.SesionRepository;
import com.devx.software.ferias.Repositories.Shared.AddressesRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import com.devx.software.ferias.Services.Logs.SessionsService;
import com.devx.software.ferias.Services.Profiles.ProfileMenuService;
import com.devx.software.ferias.Services.Users.UserProfileService;
import com.devx.software.ferias.Services.Logs.LogsService;
//import com.devx.software.ferias.Services.Profiles.PermisosPerfilesService;
import com.devx.software.ferias.Services.Profiles.ProfileMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthMapper authMapper;
    private final ProfileMapper profileMapper;
    private final ProfileAuthMapper profileAuthMapper;

    private final EnterprisesService enterprisesService;
    private final ProfileMenuService permisosPerfilesService;
    private final ProfileMenuService profileMenuService;
    private final SessionsService sessionsService;
    private final UserProfileService userProfileService;
    private final JwtUtilService jwtUtilService;
    private final UsersDetailsServiceImpl usersDetailsServiceImpl;

    private final AddressesRepository addressesRepository;
    private final SesionRepository sesionRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public AuthService(
            PasswordEncoder passwordEncoder,

            AuthMapper authMapper,
            ProfileMapper profileMapper,
            ProfileAuthMapper profileAuthMapper,

            EnterprisesService enterprisesService,
            LogsService logsService,
            ProfileMenuService permisosPerfilesService,
            ProfileMenuService profileMenuService,
            SessionsService sessionsService,
            UserProfileService userProfileService,
            JwtUtilService jwtUtilService,
            UsersDetailsServiceImpl usersDetailsServiceImpl,

            AddressesRepository addressesRepository,
            SesionRepository sesionRepository,
            UsersRepository usersRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
        this.profileMapper = profileMapper;
        this.profileAuthMapper = profileAuthMapper;

        this.enterprisesService = enterprisesService;
        this.permisosPerfilesService = permisosPerfilesService;

        this.profileMenuService = profileMenuService;
        this.sessionsService = sessionsService;
        this.userProfileService = userProfileService;
        this.jwtUtilService = jwtUtilService;
        this.usersDetailsServiceImpl = usersDetailsServiceImpl;

        this.addressesRepository = addressesRepository;
        this.sesionRepository = sesionRepository;
        this.usersRepository = usersRepository;
    }

    public static int getRandomIndex(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        UserEntity usuario = this.usersRepository.userAuthentication(loginRequest.getEmail());

        if (usuario == null) {
            System.out.println("A");
            throw new Exception("El usuario no está registrado");
        }
        if (!this.passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            System.out.println("B");
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        if (!usuario.getEstatus()) {
            System.out.println("C");
            throw new Exception("El usuario aún no ha sido activado");
        }

        UserEntity user = this.usersRepository.userAuthentication(loginRequest.getEmail());
        final UserDetails userDetails = usersDetailsServiceImpl.loadUserByUsername(user.getEmail());
        final String jwt = jwtUtilService.generateToken(userDetails);

        LoginResponse loginResponse = this.authMapper.entityToDTO(usuario);
        SessionEntity session = this.sessionsService.createSessionLog(usuario);
        session.setUsuario(null);

        loginResponse.setToken(jwt);
        loginResponse.setSesionActiva(false);
        loginResponse.setPerfiles(this.getUserProfilesList(usuario.getId()));
        loginResponse.setLogsSesion(session);

        return loginResponse;
    }

    public SessionEntity updateProfileSession(Long sessionId, Long profileId) throws Exception {
        return this.sessionsService.updateProfileSesion(sessionId, profileId);
    }

    public LogoutResponse logout(LogoutRequest logoutRequest) throws Exception {
        SessionEntity sesion = this.sesionRepository.findById(logoutRequest.getSessionId()).get();
        if (sesion != null) {
            sesion.setEstatus(false);
            sesion.setSesionFin(new Date());
            this.sesionRepository.save(sesion);
            LogoutResponse logoutResponse = new LogoutResponse();
            logoutResponse.setMessage("La sesión fue cerrada de manera exitosa");
            return logoutResponse;
        }
        throw new Exception("La sesión no puede ser eliminada porque no existe registro de la misma");
    }

    public UserEntity passwordRecovery(PasswordRecoveryRequest passwordRecoveryRequest) throws Exception {
        UserEntity userEntity = this.usersRepository.userAuthentication(passwordRecoveryRequest.getEmail());
        if (userEntity != null) {
            if (userEntity.getEstatus()) {
                Mailgun mailgun = new Mailgun();
                String newPassword = this.createRandomPassword();
                userEntity.setUpdatedAt(new Date());
                userEntity.setPassword(this.passwordEncoder.encode(newPassword));

                UserEntity response = this.usersRepository.save(userEntity);
                mailgun.sendBasicEmail("Recuperación de contraseña - Tu estado industrial", passwordRecoveryRequest.getEmail(), this.passwordRecoveryEmailContent(userEntity.getNombre(), newPassword));
                return response;
            }
            throw new Exception("El usuario ingresado para recuperar la contraseña no está habilitado en la plataforma");
        }
        throw new Exception("El email ingresado para recuperar la contraseña no está registrado en la plataforma");
    }

    public String refreshTokenByUserId(RefreshTokenByUserIdRequest refreshTokenByUserIdRequest, String oldToken) throws Exception {
        UserEntity userEntity = this.usersRepository.findUserById(refreshTokenByUserIdRequest.getUserId());
        if (userEntity != null) {
            SessionEntity sesion = this.sessionsService.findActiveSessionLogByUserId(refreshTokenByUserIdRequest.getUserId());
            if (sesion != null) {
                String newToken = this.jwtUtilService.refreshToken(oldToken);
                sesion.setSesionFin(this.jwtUtilService.extractExpiration(newToken));
                this.sessionsService.updateSession(sesion);
                return newToken;
            } else {
                sesion = this.sessionsService.createSessionLog(userEntity);
                String newToken = this.jwtUtilService.refreshToken(oldToken);
                sesion.setSesionFin(this.jwtUtilService.extractExpiration(newToken));
                return newToken;
            }
        }
        throw new Exception("El usuario solicitado no está registrado en la plataforma");
    }

    public EnterpriseEntity ownEnterpriseRegistration(EnterpriseRegistrationoRequest enterpriseRegistrationoRequest) throws Exception {
        if (enterpriseRegistrationoRequest.getEmpresa() != null) {
            return this.enterprisesService.ownEnterpriseRegistration(enterpriseRegistrationoRequest.getEmpresa());
        }
        throw new Exception("No hay datos para registrar");
    }

    public UserEntity myProfile(Long userId) throws Exception {
        UserEntity myProfile = this.usersRepository.findUserById(userId);
        if (myProfile != null) {
            return myProfile;
        }
        throw new Exception("El perfil solicitado no existe");
    }

    public UserEntity updateProfile(MyProfileRequest myProfileRequest) throws Exception {
        UserEntity usuario = this.usersRepository.findUserById(myProfileRequest.getUsuario().getId());
        UserEntity requestUserEntity = myProfileRequest.getUsuario();
        usuario.setNombre(requestUserEntity.getNombre());
        usuario.setEmail(requestUserEntity.getEmail());
        usuario.setTelefono(requestUserEntity.getTelefono());
        usuario.setSexo(requestUserEntity.getSexo());
        usuario.setRecibirPublicidad(requestUserEntity.getRecibirPublicidad());
        if (myProfileRequest.getActualizarPassword()) {
            if (myProfileRequest.getNuevaPassword() == null) {
                throw new Exception("La nueva contraseña es requerida");
            }
            if (myProfileRequest.getRepetirPassword() == null) {
                throw new Exception("La repetición de la contraseña es requerida");
            }
            if (myProfileRequest.getNuevaPassword().length() < 8) {
                throw new Exception("La nueva contraseña debe contener mínimo 8 caracteres");
            }
            if (myProfileRequest.getRepetirPassword().length() < 8) {
                throw new Exception("La repetición de la contraseña debe contener mínimo 8 caracteres");
            }
            if (!myProfileRequest.getNuevaPassword().equals(myProfileRequest.getRepetirPassword())) {
                throw new Exception("Las contraseñas no coinciden, verifica la información");
            }
            usuario.setPassword(this.passwordEncoder.encode(myProfileRequest.getNuevaPassword()));
            usuario = this.usersRepository.save(usuario);
        }
        if(!requestUserEntity.getDomicilios().isEmpty()){
            if(!usuario.getDomicilios().isEmpty()){
                usuario.setDomicilios(new ArrayList<>());
            }
            DomicilioEntity domicilio = this.addressesRepository.save(requestUserEntity.getDomicilios().get(0));
            usuario.addDomicilio(domicilio);
        }
        return this.usersRepository.save(usuario);
    }

    private String passwordRecoveryEmailContent(String username, String password) {
        System.out.println(password);
        String htmlContent = "<div>\n" +
                "                <p>\n" +
                "                    Hola <b><i>{{username}}</i></b>:\n" +
                "                    <br>\n" +
                "                    Esta es la nueva contraseña para que inicies sesión en la plataforma <b>Veracruz, Tu estado industrial</b>:\n" +
                "                </p>\n" +
                "                <h2>{{password}}</h2>\n" +
                "                <br><br>\n" +
                "                <p>Más adelante, si lo deseas puedes actualizar tu contraseña en tu sección <b><i>Mi perfil</i></b></p>" +
                "                <br>\n" +
                "            </div>";
        htmlContent = htmlContent.replace("{{username}}", username);
        htmlContent = htmlContent.replace("{{password}}", password);

        return htmlContent;
    }

    public String createRandomPassword() {
        String randomPassword = "";
        String validPasswordCharacters = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int x = 0; x < 25; x++) {
            randomPassword += validPasswordCharacters.charAt(getRandomIndex(validPasswordCharacters.length()));
        }
        return randomPassword;
    }

    private List<ProfileAuthDTO> getUserProfilesList(Long usuarioId) {
        List<UserProfileEntity> usersProfiles = this.userProfileService.findAllByUsuarioId(usuarioId);
        List<ProfileAuthDTO> profiles = new ArrayList<ProfileAuthDTO>();
        for (UserProfileEntity userProfileEntity : usersProfiles) {
            ProfileAuthDTO profile = this.profileAuthMapper.entityToDTO(userProfileEntity.getPerfil());

            List<ProfileMenuEntity> profileMenuEntities = this.profileMenuService.findByPerfilId(profile.getId());
            List<PermissionDTO> permissionDTOS = new ArrayList<PermissionDTO>();

            for (ProfileMenuEntity profileMenuEntity : profileMenuEntities) {
                PermissionDTO permissionDTO = new PermissionDTO();
                if (profileMenuEntity.getMenu().getSubmenus().size() > 0) {
                    permissionDTO.setSubModulos(new ArrayList<ChildPermissionDTO>());
                    for (MenuEntity submenu : profileMenuEntity.getMenu().getSubmenus()) {
                        ChildPermissionDTO childPermissionDTO = new ChildPermissionDTO();
                        childPermissionDTO.setNombreEtiqueta(submenu.getEtiqueta());
                        childPermissionDTO.setUrlModulo(submenu.getUrl());
                        permissionDTO.getSubModulos().add(childPermissionDTO);
                    }
                } else {
                    permissionDTO.setSubModulos(null);
                }
                permissionDTO.setCanCreate(profileMenuEntity.getCrear());
                permissionDTO.setCanUpdate(profileMenuEntity.getActualizar());
                permissionDTO.setCanDelete(profileMenuEntity.getEliminar());
                permissionDTO.setCanShow(profileMenuEntity.getLeer());
                permissionDTO.setCanReport(profileMenuEntity.getReportar());
                permissionDTO.setIcon(profileMenuEntity.getMenu().getIcono());
                permissionDTO.setId(profileMenuEntity.getMenu().getId());
                permissionDTO.setNombreEtiqueta(profileMenuEntity.getMenu().getEtiqueta());
                permissionDTO.setNombreRol(profileMenuEntity.getMenu().getNombre());
                permissionDTO.setUrlModulo(profileMenuEntity.getMenu().getUrl());
                permissionDTOS.add(permissionDTO);
            }
            profile.setPermisos(permissionDTOS);
            profiles.add(profile);
        }
        return profiles;
    }
}
