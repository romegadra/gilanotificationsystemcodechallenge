package com.gila.notifications.gilanotificationsystem.dto;

import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.enums.NotificationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationLogResponse {

    private Long id;
    private MessageCategory messageCategory;
    private String messageBody;
    private NotificationChannel notificationChannel;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private NotificationStatus status;
    private String errorMessage;
    private LocalDateTime createdAt;
}
