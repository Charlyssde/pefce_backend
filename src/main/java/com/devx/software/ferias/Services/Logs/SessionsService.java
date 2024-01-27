package com.devx.software.ferias.Services.Logs;

import com.devx.software.ferias.Entities.Logs.SessionEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Repositories.Logs.SesionRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SessionsService {
    private final SesionRepository sesionRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public SessionsService(
            SesionRepository sesionRepository,
            UsersRepository usersRepository
    ) {
        this.sesionRepository = sesionRepository;
        this.usersRepository = usersRepository;
    }

    public Page<SessionEntity> pageSession(Pageable pageable, Long userId, String nombre) throws Exception {
        if (userId == null) {
            return this.sesionRepository.findAllByOrderByIdDesc(pageable, nombre);
        } else {
            return this.sesionRepository.findAllByUsuarioOrderByIdDesc(pageable, userId);
        }
    }

    public SessionEntity createSessionLog(UserEntity user) throws Exception {
        if (user != null) {
            this.closeAllActiveSessionLogssByUserId(user.getId());
            Long perfil = null;
            if (user.getPerfiles().size() == 1) {
                perfil = user.getPerfiles().get(0).getId();
            }
            return this.sesionRepository.save(new SessionEntity(user, perfil, new Date(), new Date(System.currentTimeMillis() + 3600000), true));
        }
        throw new Exception("No existe el usuario para crear la sesión");
    }

    public SessionEntity findActiveSessionLogByUserId(Long userId) {
        return this.sesionRepository.findSesionEntityByEstatusTrueAndUsuario_IdOrderByIdDesc(userId);
    }

    public SessionEntity updateSession(SessionEntity sesion) {
        return this.sesionRepository.save(sesion);
    }

    public SessionEntity updateProfileSesion(Long sessionId, Long profileId) throws Exception {
        SessionEntity session = this.sesionRepository.findById(sessionId).get();
        if (session != null) {
            session.setPerfil(profileId);
            return this.sesionRepository.save(session);
        }
        throw new Exception("La sesión no existe");
    }

    public List<SessionEntity> getAllActiveSessionLogsByUserId(Long userId) throws Exception {
        return this.sesionRepository.findAllByEstatusTrueAndSesionFinNotNullAndUsuario_Id(userId);
    }

    private void closeAllActiveSessionLogssByUserId(Long userId) throws Exception {
        List<SessionEntity> activeSessions = this.getAllActiveSessionLogsByUserId(userId);
        for (SessionEntity activeSession : activeSessions) {
            activeSession.setEstatus(false);
            activeSession.setSesionFin(new Date());
            this.sesionRepository.save(activeSession);
        }
    }
}
