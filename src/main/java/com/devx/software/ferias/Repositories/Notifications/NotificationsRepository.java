package com.devx.software.ferias.Repositories.Notifications;

import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long> {
    //List<NotificationsEntity> findAllNotificationsByUser(Long usuarioId);
    List<NotificationsEntity> findAllNotificationsByDestinatario(Long usuarioId);
    NotificationsEntity findNotificacionesById(Long id);

    List<NotificationsEntity> findNotificacionesByDestinatario_Id(Long id);

    @Query(
            value = "SELECT n.* " +
                    "FROM a_notificaciones n " +
                    "INNER JOIN a_notificaciones_canales c ON n.canal_id = c.id " +
                    "INNER JOIN a_notificaciones_canales_usuario cu ON c.id = cu.canal_id " +
                    "WHERE c.tipo = 2 AND cu.usuario_id = ?1",
            nativeQuery = true
    )
    List<NotificationsEntity> findNotificacionesByCanalTipo2AndIdUsuario(Long id);

    @Query(
            value = "SELECT n.* FROM a_notificaciones n " +
                    "INNER JOIN a_notificaciones_canales c ON n.canal_id = c.id " +
                    "INNER JOIN a_notificaciones_canales_perfil cp ON c.id = cp.canal_id " +
                    "INNER JOIN a_usuario__a_perfil up ON cp.perfil_id = up.perfil_id " +
                    "WHERE c.tipo = 1 AND up.usuario_id = ?1",
            nativeQuery = true)
    List<NotificationsEntity> findNotificacionesByCanalTipo1AndIdUsuario(Long id);

    @Query(
            value = "SELECT n.* " +
                    "FROM a_notificaciones n " +
                    "WHERE n.destinatario_id = :userId " +
                    "AND n.vista = false " +
                    "LIMIT :number",
            nativeQuery = true
    )
    List<NotificationsEntity> getLastNotifications(Long number, Long userId);
}
