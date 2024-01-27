package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Auth.*;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public AuthController(
            AuthService authService,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() throws Exception {
        try {
            this.headers.set("200", "Test exitoso");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return new ResponseEntity<>(auth, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) throws Exception {
        try{
            try {
                this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
            } catch (BadCredentialsException e) {
                throw new Exception("Las credenciales son incorrectas, revisa tu información.", e);
            }
            System.out.println("C-A");
            LoginResponse loginResponse = this.authService.login(loginRequest);
            this.headers.set("200", "Inicio de sesión exitoso");
            System.out.println("C-B");
            return new ResponseEntity<>(loginResponse, this.headers, HttpStatus.OK);
        }
        catch(Exception e){

            return new ResponseEntity( e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/session/{sessionId}/profile/{profileId}")
    public ResponseEntity<?> setLoggedProfile(
            @PathVariable Long sessionId,
            @PathVariable Long profileId
    ) throws Exception {
        try {
            this.headers.set("200", "Asignación de perfil exitosa");
            return new ResponseEntity<>(this.authService.updateProfileSession(sessionId, profileId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest logoutRequest) {
        try {
            LogoutResponse logoutResponse = this.authService.logout(logoutRequest);
            this.headers.set("200", "Cierre de sesión exitoso");
            return new ResponseEntity<>(logoutResponse, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/password-recovery")
    public ResponseEntity<PasswordRecoveryResponse> passwordRecovery(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest) {
        try {
            this.authService.passwordRecovery(passwordRecoveryRequest);
            this.headers.set("200", "Recuperar contraseña exitoso");
            PasswordRecoveryResponse passwordRecoveryResponse = new PasswordRecoveryResponse();
            passwordRecoveryResponse.setMessage("Se ha enviado una nueva contraseña al email " + passwordRecoveryRequest.getEmail());
            return new ResponseEntity<>(passwordRecoveryResponse, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenByUserIdResponse> refreshToken(
            @RequestBody RefreshTokenByUserIdRequest refreshTokenByUserIdRequest,
            @RequestHeader("Authorization") String oldToken) {
        try {
            RefreshTokenByUserIdResponse refreshTokenByUserIdResponse = new RefreshTokenByUserIdResponse();
            this.headers.set("200", "Token recargado exitoso");
            refreshTokenByUserIdResponse.setToken("Bearer " + this.authService.refreshTokenByUserId(refreshTokenByUserIdRequest, oldToken.replace("Bearer ", "")));
            return new ResponseEntity<>(refreshTokenByUserIdResponse, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enterprise-registration")
    public ResponseEntity<EnterpriseEntity> ownEnterpriseRegistration(@RequestBody EnterpriseRegistrationoRequest enterpriseRegistrationoRequest) {
        try {
            this.headers.set("200", "Solicitud de registro de empresa recibida con exitosamente");
            return new ResponseEntity<>(this.authService.ownEnterpriseRegistration(enterpriseRegistrationoRequest), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("El correo o el RFC ya se encuentran registrados!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-profile/{userId}")
    public ResponseEntity<UserEntity> myProfile(@PathVariable Long userId) {
        try {
            UserEntity userEntity = this.authService.myProfile(userId);
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(userEntity, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/my-profile")
    public ResponseEntity<Object> updateProfile(@RequestBody MyProfileRequest myProfileRequest) {
        try {
            UserEntity userEntity = this.authService.updateProfile(myProfileRequest);
            this.headers.set("200", "Perfil guardado de manera exitosa");
            return new ResponseEntity<>(userEntity, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
