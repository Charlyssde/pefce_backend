package com.devx.software.ferias.Services.Notifications;

import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Repositories.Notifications.NotificationsRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {

    private final NotificationsRepository notificacionesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public NotificationsService(
            NotificationsRepository notificacionesRepository,
            UsersRepository usersRepository
    ) {
        this.notificacionesRepository = notificacionesRepository;
        this.usersRepository = usersRepository;
    }

    public NotificationsEntity create(NotificationsEntity entity) throws Exception {
        try {
            return this.notificacionesRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<NotificationsEntity> page() {
        return this.notificacionesRepository.findAll();
    }

    public NotificationsEntity findById(Long id) {
        return this.notificacionesRepository.findNotificacionesById(id);
    }

    public NotificationsEntity update(NotificationsEntity entity) {
        return this.notificacionesRepository.save(entity);
    }

    public void deleteById(Long id) {
        this.notificacionesRepository.deleteById(id);
    }

    public List<NotificationsEntity> findByDestinatarioId(Long id) {
        return this.notificacionesRepository.findNotificacionesByDestinatario_Id(id);
    }

    public List<NotificationsEntity> findByCanalTipo2AndUsuarioId(Long id) {
        return this.notificacionesRepository.findNotificacionesByCanalTipo2AndIdUsuario(id);
    }

    public List<NotificationsEntity> findByCanalTipo1AndUsuarioId(Long id) {
        return this.notificacionesRepository.findNotificacionesByCanalTipo1AndIdUsuario(id);
    }


    public List<NotificationsEntity> getLastNotifications(Long number){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = this.usersRepository.userAuthentication(email);
        return this.notificacionesRepository.getLastNotifications(number,userEntity.getId());
    }
}
