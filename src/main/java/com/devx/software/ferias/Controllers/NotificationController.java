package com.devx.software.ferias.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/notify")
    public String getNotification(NotificationsEntity not) {

        // Push notifications to front-end
        template.convertAndSend("/topic/notification", not);

        return "Notifications successfully sent to Angular !";
    }
    
}