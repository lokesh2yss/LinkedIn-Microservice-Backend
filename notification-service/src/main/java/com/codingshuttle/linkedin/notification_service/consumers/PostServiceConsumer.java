package com.codingshuttle.linkedin.notification_service.consumers;

import com.codingshuttle.linkedin.notification_service.clients.ConnectionClient;
import com.codingshuttle.linkedin.notification_service.dto.PersonDto;
import com.codingshuttle.linkedin.notification_service.entities.Notification;
import com.codingshuttle.linkedin.notification_service.entities.enums.NotificationType;
import com.codingshuttle.linkedin.notification_service.repositories.NotificationRepository;
import com.codingshuttle.linkedin.post_service.events.PostCreatedEvent;
import com.codingshuttle.linkedin.post_service.events.PostLikedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceConsumer {
    private final ConnectionClient connectionClient;
    private final NotificationRepository notificationRepository;
    //private final KafkaTemplate<Long, >
    @KafkaListener(topics = "post-created-topic")
    public void HandlePostCreatedEvent(PostCreatedEvent postCreatedEvent) {
        log.info("Sending notifications to 1st degree connections: HandlePostCreatedEvent: {}", postCreatedEvent);
        List<PersonDto> firstDegreeConnections = connectionClient.getFirstConnections(postCreatedEvent.getCreatorId());
        for(PersonDto personDto: firstDegreeConnections) {
            String message = String.format("Your connection %s has created a new post. Please check it out", postCreatedEvent.getCreatorId());
            sendNotification(personDto.getUserId(), message);
        }
    }

    @KafkaListener(topics = "post-liked-topic")
    public void handlePostLikedEvent(PostLikedEvent postLikedEvent) {
        log.info("Sending notifications: handlePostLikedEvent {}", postLikedEvent);
        String message = String.format("Your post, %d has been liked by %d", postLikedEvent.getPostId(), postLikedEvent.getLikedByUserId());
        sendNotification(postLikedEvent.getCreatorId(), message);
    }

    public void sendNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setNotificationType(NotificationType.NOTIFY_POST_CREATED);

        notificationRepository.save(notification);
    }
}