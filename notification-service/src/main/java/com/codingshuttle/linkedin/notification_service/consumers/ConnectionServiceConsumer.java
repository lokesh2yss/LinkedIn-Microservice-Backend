package com.codingshuttle.linkedin.notification_service.consumers;

import com.codingshuttle.linkedin.connection_service.events.AcceptConnectionRequestEvent;
import com.codingshuttle.linkedin.connection_service.events.SendConnectionRequestEvent;
import com.codingshuttle.linkedin.notification_service.entities.enums.NotificationType;
import com.codingshuttle.linkedin.notification_service.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceConsumer {
    private final NotificationService notificationService;
    @KafkaListener(topics = "send-connection-request-topic")
    public void handleSendConnectionRequest(SendConnectionRequestEvent sendConnectionRequestEvent) {
        log.info("Sending notifications: handleSendConnectionRequest {}", sendConnectionRequestEvent);
        String message = String.format("You have received a connection request from user with id %d", sendConnectionRequestEvent.getSenderId());
        notificationService.sendNotification(sendConnectionRequestEvent.getReceiverId(), message, NotificationType.SEND_CONNECTION_REQUEST);
    }

    @KafkaListener(topics = "accept-connection-request-topic")
    public void handleAcceptConnectionRequest(AcceptConnectionRequestEvent acceptConnectionRequestEvent) {
        log.info("Sending notifications: handleAcceptConnectionRequest {}", acceptConnectionRequestEvent);
        String message = String.format("Your connection request has been accepted by the user with id %d", acceptConnectionRequestEvent.getReceiverId());
        notificationService.sendNotification(acceptConnectionRequestEvent.getSenderId(), message, NotificationType.ACCEPT_CONNECTION_REQUEST);
    }
}
