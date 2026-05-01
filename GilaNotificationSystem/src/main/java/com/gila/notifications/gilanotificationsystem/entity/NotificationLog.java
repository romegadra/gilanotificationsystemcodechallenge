package com.gila.notifications.gilanotificationsystem.entity;

import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="notification_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageCategory messageCategory;

    @Column(nullable = false, length = 1000)
    private String messageBody;

    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChannel;

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String errorMessage;

    private LocalDateTime createdAt;
}
