package com.devx.software.ferias.Services.Users;

import com.devx.software.ferias.DTOs.Auth.PasswordRecoveryRequest;
import com.devx.software.ferias.DTOs.Users.UserDTO;
import com.devx.software.ferias.DTOs.Users.UserInstitutionSelectDTO;
import com.devx.software.ferias.DTOs.Users.UserRequestDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import com.devx.software.ferias.Mail.EmailService;
import com.devx.software.ferias.Mappers.Profile.ProfileMapper;
import com.devx.software.ferias.Mappers.Users.FormResourcesUserMapper;
import com.devx.software.ferias.Mappers.Users.UserInstitutionSelectMapper;
import com.devx.software.ferias.Mappers.Users.UserMapper;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
//import com.devx.software.ferias.DTOs.Users.UserDTO;
//import com.devx.software.ferias.Mappers.Users.UserMapper;
//import com.devx.software.ferias.Repositories.Users.UsersRepository;
//
//import com.devx.software.ferias.zx_services.DirectorioUserService;
//import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;
    private final FormResourcesUserMapper formResourcesUserMapper;
    private final UserInstitutionSelectMapper userInstitutionSelectMapper;
    private final ProfileMapper profileMapper;
    private final UserMapper userMapper;
    private final EmailService emailService;

    @Autowired
    public UserService(
            PasswordEncoder passwordEncoder,
            UsersRepository usersRepository,
            ProfileRepository profileRepository,
            FormResourcesUserMapper formResourcesUserMapper,
            UserInstitutionSelectMapper userInstitutionSelectMapper,
            ProfileMapper profileMapper,
            UserMapper userMapper,
            EmailService emailService
    ) {
        this.passwordEncoder = passwordEncoder;

        this.usersRepository = usersRepository;
        this.profileRepository = profileRepository;
        this.formResourcesUserMapper = formResourcesUserMapper;
        this.userInstitutionSelectMapper = userInstitutionSelectMapper;
        this.profileMapper = profileMapper;
        this.userMapper = userMapper;
        this.emailService = emailService;
    }

    public static int getRandomIndex(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    public String createRandomPassword() {
        String randomPassword = "";
        String validPasswordCharacters = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int x = 0; x < 25; x++) {
            randomPassword += validPasswordCharacters.charAt(getRandomIndex(validPasswordCharacters.length()));
        }
        return randomPassword;
    }


    public UserEntity findUserById(Long userId) throws Exception {
        UserEntity userEntity = this.usersRepository.findUserById(userId);
        if (userEntity != null) {
            return userEntity;
        }
        throw new Exception("El usuario no existe");
    }

    public List<UserEntity> getUsersByProfileId(Long profileId) {
        return this.usersRepository.getAllUsersByProfileId(profileId);
    }

    public List<UserEntity> getAllUsers() {
        return this.usersRepository.findUserEntitiesByOrderByIdDesc();
    }

    public Page<UserEntity> pageUsers(Pageable pageable, String nombre) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);

        if (activeProfileEntity != null) {
            if (activeProfileEntity.getTipo().equals("root") && activeProfileEntity.getNombre().equals("Superadministrador")) {
                if (nombre == null) {
                    return this.usersRepository.findUserEntitiesByOrderByIdDesc(pageable);
                }
                return this.usersRepository.findUserEntitiesByNombreContainingIgnoreCaseOrderByIdDesc(pageable, nombre);
            }
            if (activeProfileEntity.getTipo().equals("root") && activeProfileEntity.getNombre().equals("Administrador")) {
                if (nombre == null) {
                    return this.usersRepository.findUserEntitiesByAdministratorProfile(pageable);
                }
                return this.usersRepository.findUserEntitiesByNombreContainingIgnoreCaseOrderByIdDesc(pageable, nombre);
            }
            if (activeProfileEntity.getTipo().equals("institución")) {
                Long nivel = activeProfileEntity.getNivel();
                String area = activeProfileEntity.getArea();
                if (nivel == 1) {
                    if (nombre == null) {
                        return this.usersRepository.findUserEntitiesByInstitutionProfileLevelOne(pageable);
                    }
                    return this.usersRepository.findUserEntitiesByInstitutionProfileLevelOneAndNombreContains(pageable, nombre);
                }
                if (nivel > 1) {
                    if (nombre == null) {
                        return this.usersRepository.findUserEntitiesByInstitutionProfileLevelPlusOne(pageable, nivel, area);
                    }
                    return this.usersRepository.findUserEntitiesByInstitutionProfileLevelPlusOneAndNombreContains(pageable, nivel, area, nombre);
                }
            }
            throw new Exception("El usuario no tiene acceso a los recursos");
        }
        throw new Exception("El usuario no tiene una sesión iniciada");
    }


    public List<ProfileEntity> getAllInstitutionalProfiles() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);

        if (activeProfileEntity != null) {
            if (activeProfileEntity.getNombre().equals("Superadministrador")) {
                return this.profileRepository.findAllByOrderByIdAsc(Sort.by("nivel").and(Sort.by("area")));
            }
            if (activeProfileEntity.getNombre().equals("Administrador")) {
                return this.profileRepository.findProfileEntityByTipoOrderByNivelAsc("institución", Sort.by("nivel").and(Sort.by("area")));
            }
            if (activeProfileEntity.getTipo().equals("institución")) {
                return this.profileRepository.findChildProfilesByNivelAndArea(activeProfileEntity.getNivel(), activeProfileEntity.getArea());
            }
        }
        throw new Exception("El usuario no tiene una sesión iniciada");
    }

    public UserEntity saveUser(UserRequestDTO userRequestDTO) throws Exception {
        String newPassword = this.createRandomPassword();
        UserEntity userRequest = this.formResourcesUserMapper.dtoToEntity(userRequestDTO.getUser());
        if (!userRequestDTO.getUpdatePassword()) {
            userRequest.setPassword(this.passwordEncoder.encode(newPassword));
        }
        if (userRequestDTO.getUpdatePassword()) {
            if (userRequestDTO.getNewPassword() == null) {
                throw new Exception("La nueva contraseña es requerida");
            }
            if (userRequestDTO.getRepeatPassword() == null) {
                throw new Exception("La repetición de la contraseña es requerida");
            }
            if (userRequestDTO.getNewPassword().length() < 8) {
                throw new Exception("La nueva contraseña debe contener mínimo 8 caracteres");
            }
            if (userRequestDTO.getRepeatPassword().length() < 8) {
                throw new Exception("La repetición de la contraseña debe contener mínimo 8 caracteres");
            }
            if (!userRequestDTO.getNewPassword().equals(userRequestDTO.getRepeatPassword())) {
                throw new Exception("Las contraseñas no coinciden, verifica la información");
            }
            newPassword = userRequestDTO.getNewPassword();
            userRequest.setPassword(this.passwordEncoder.encode(newPassword));
        }
        List<ProfileEntity> profiles = userRequestDTO.getProfiles();
        userRequest = this.usersRepository.save(userRequest);
        userRequest.setPerfiles(new ArrayList<>());
        for (ProfileEntity profile : profiles) {
            userRequest.addPerfil(profile);
        }
        UserEntity response = this.usersRepository.save(userRequest);
        /*Mailgun mailgun = new Mailgun();
        mailgun.sendBasicEmail(
                "Registro de nuevo usuario - Tu estado industrial",
                userRequest.getEmail(),
                this.registrationEmailContent(userRequest.getNombre(), userRequest.getEmail(), newPassword)
        );*/
        this.emailService.sendEmail(
                userRequest.getEmail(), 
                "Registro de nuevo usuario - Tu estado industrial", 
                this.registrationEmailContent(userRequest.getNombre(), userRequest.getEmail(), newPassword)
                );
        return response;
    }


    public UserEntity updateUser(Long userId, UserRequestDTO userRequestDTO) throws Exception {
        UserEntity user = this.usersRepository.findUserById(userId);
        if (user != null) {
            UserEntity userRequest = this.formResourcesUserMapper.dtoToEntity(userRequestDTO.getUser());
            if (!userRequestDTO.getUpdatePassword()) {
                userRequest.setPassword(user.getPassword());
            }
            if (userRequestDTO.getUpdatePassword()) {
                if (userRequestDTO.getNewPassword() == null) {
                    throw new Exception("La nueva contraseña es requerida");
                }
                if (userRequestDTO.getRepeatPassword() == null) {
                    throw new Exception("La repetición de la contraseña es requerida");
                }
                if (userRequestDTO.getNewPassword().length() < 8) {
                    throw new Exception("La nueva contraseña debe contener mínimo 8 caracteres");
                }
                if (userRequestDTO.getRepeatPassword().length() < 8) {
                    throw new Exception("La repetición de la contraseña debe contener mínimo 8 caracteres");
                }
                if (!userRequestDTO.getNewPassword().equals(userRequestDTO.getRepeatPassword())) {
                    throw new Exception("Las contraseñas no coinciden, verifica la información");
                }
                userRequest.setPassword(this.passwordEncoder.encode(userRequestDTO.getNewPassword()));
            }
            List<ProfileEntity> profiles = userRequestDTO.getProfiles();
            userRequest.setPerfiles(new ArrayList<>());
            for (ProfileEntity profile : profiles) {
                userRequest.addPerfil(profile);
            }
            this.emailService.sendEmail(
                userRequest.getEmail(),
                "Actualización de su registro de usuario - Tu estado industrial",
                "Se ha realizado una actualización en el registro de tu usuario, si no solicitaste este cambio contacta al administrador"
                );
            /*Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail(
                    "Actualización de su registro de usuario - Tu estado industrial",
                    userRequest.getEmail(),
                    "Se ha realizado una actualización en el registro de tu usuario, si no solicitaste este cambio contacta al administrador"
            );*/
            return this.usersRepository.save(userRequest);
        }
        throw new Exception("El usuario no existe");
    }

    public UserEntity updateStatusUser(Long userId, Boolean status) throws Exception {
        UserEntity userEntity = this.usersRepository.findUserById(userId);
        if (userEntity != null) {
            userEntity.setEstatus(status);
            return this.usersRepository.save(userEntity);
        }
        throw new Exception("El usuario no existe");
    }

    public HashMap<String, Object> deleteUser(Long userId) throws Exception {
        UserEntity userEntity = this.findUserById(userId);
        if (userEntity != null) {
            this.usersRepository.deleteById(userId);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Usuario eliminado");
            return response;
        }
        throw new Exception("El usuario no existe");
    }

    public UserEntity passwordRecovery(PasswordRecoveryRequest passwordRecoveryRequest) throws Exception {
        UserEntity userEntity = this.usersRepository.userAuthentication(passwordRecoveryRequest.getEmail());
        if (userEntity != null) {
            if (userEntity.getEstatus()) {
                //Mailgun mailgun = new Mailgun();
                String newPassword = this.createRandomPassword();
                userEntity.setUpdatedAt(new Date());
                userEntity.setPassword(this.passwordEncoder.encode(newPassword));
                UserEntity response = this.usersRepository.save(userEntity);
                //mailgun.sendBasicEmail("Recuperación de contraseña - Tu estado industrial", passwordRecoveryRequest.getEmail(), this.passwordRecoveryEmailContent(userEntity.getNombre(), newPassword));
                this.emailService.sendEmail(
                    passwordRecoveryRequest.getEmail(),
                    "Recuperación de contraseña - Tu estado industrial",
                    this.passwordRecoveryEmailContent(userEntity.getNombre(), newPassword)
                    );
                return response;
            }
            throw new Exception("El usuario ingresado para recuperar la contraseña no está habilitado en la plataforma");
        }
        throw new Exception("El email ingresado para recuperar la contraseña no está registrado en la plataforma");
    }


    private String registrationEmailContent(String name, String email, String password) {
        String htmlContent = "<div>\n" +
                "                <p>\n" +
                "                    Hola <b><i>{{name}}</i></b>:\n" +
                "                    <br><br>\n" +
                "                    Estas son las credenciales para que inicies sesión en la plataforma <b>Veracruz, Tu estado industrial</b>:\n" +
                "                </p>\n" +
                "                <h2>Correo electrónico: <b>{{email}}</b></h2>\n" +
                "                <br>\n" +
                "                <h2>Contraseña: <b>{{password}}</b></h2>\n" +
                "                <br><br>\n" +
                "                <p>Más adelante, si lo deseas puedes actualizar tu contraseña en tu sección <b><i>Mi perfil</i></b></p>" +
                "                <br>\n" +
                "            </div>";
        htmlContent = htmlContent.replace("{{name}}", name);
        htmlContent = htmlContent.replace("{{email}}", email);
        htmlContent = htmlContent.replace("{{password}}", password);

        return htmlContent;
    }

    private String passwordRecoveryEmailContent(String username, String password) {
        String htmlContent = "<div>\n" +
                "                <p>\n" +
                "                    Hola <b><i>{{username}}</i></b>:\n" +
                "                    <br><br>\n" +
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

    public UserEntity findByEmail(String email) {
        return usersRepository.userAuthentication(email);
    }




    // SCOPES
    public List<UserEntity> getAllUsersWhereProfileIsInstitution(){
        return this.usersRepository.getAllUsersWhereProfileIsInstitution();
    }

    public List<UserInstitutionSelectDTO> getAllActiveUsersByProfileArea(String area) throws Exception{
        return this.userInstitutionSelectMapper.listEntityToDTO(this.usersRepository.getAllActiveUsersByProfileArea(area));
    }

    public List<UserInstitutionSelectDTO> getAllUsersWhereProfileIsInstitution(String area) throws Exception{
        return this.userInstitutionSelectMapper.listEntityToDTO(this.usersRepository.getAllUsersWhereProfileIsInstitution());
    }

    public UserDTO findUserDTOById(Long id){
        return this.userMapper.entityToDTO(this.usersRepository.findUserById(id));
    }
    
   public List<UserEntity> findbyuserbyminuta (Long id){
        return this.usersRepository.findUsuariosByMinutaId(id);
    }
}
