package com.codingshuttle.linkedin.notification_service.repositories;

import com.codingshuttle.linkedin.notification_service.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
