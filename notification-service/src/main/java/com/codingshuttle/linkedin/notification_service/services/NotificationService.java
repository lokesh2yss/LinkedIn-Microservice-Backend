package com.codingshuttle.linkedin.notification_service.services;

import com.codingshuttle.linkedin.notification_service.entities.Notification;
import com.codingshuttle.linkedin.notification_service.entities.enums.NotificationType;
import com.codingshuttle.linkedin.notification_service.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public void sendNotification(Long userId, String message, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setNotificationType(notificationType);

        notificationRepository.save(notification);
        log.info("Successfully saved notification to db for user wit id: {}", userId);
    }
}
