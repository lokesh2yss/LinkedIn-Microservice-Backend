package com.codingshuttle.linkedin.notification_service.entities;

import com.codingshuttle.linkedin.notification_service.entities.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
