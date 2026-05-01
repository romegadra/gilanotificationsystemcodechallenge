package com.gila.notifications.gilanotificationsystem.repository;

import com.gila.notifications.gilanotificationsystem.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    List<NotificationLog> findAllByOrderByCreatedAtDesc();
}
