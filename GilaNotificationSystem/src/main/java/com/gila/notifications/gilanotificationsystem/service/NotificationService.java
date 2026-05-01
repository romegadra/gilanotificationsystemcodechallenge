package com.gila.notifications.gilanotificationsystem.service;

import com.gila.notifications.gilanotificationsystem.dto.MessageRequest;
import com.gila.notifications.gilanotificationsystem.dto.NotificationLogResponse;
import com.gila.notifications.gilanotificationsystem.dto.SendMessageResponse;
import com.gila.notifications.gilanotificationsystem.entity.NotificationLog;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.enums.NotificationStatus;
import com.gila.notifications.gilanotificationsystem.mock.User;
import com.gila.notifications.gilanotificationsystem.notification.NotificationDispatcher;
import com.gila.notifications.gilanotificationsystem.repository.NotificationLogRepository;
import com.gila.notifications.gilanotificationsystem.repository.UserMockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserMockRepository userMockRepository;
    private final NotificationDispatcher notificationDispatcher;
    private final NotificationLogRepository notificationLogRepository;

    public SendMessageResponse sendMessage(MessageRequest request) {
        List<User> subscribedUsers = userMockRepository.findAll()
                .stream()
                .filter(user -> user.getSubscribed().contains(request.getCategory()))
                .toList();

        int successCount = 0;
        int failedCount = 0;

        for (User user : subscribedUsers) {
            for (NotificationChannel channel : user.getChannels()) {
                NotificationStatus status = sendNotificationAndSaveLog(user, channel, request);

                if (status == NotificationStatus.SENT) {
                    successCount++;
                } else {
                    failedCount++;
                }
            }
        }

        return SendMessageResponse.builder()
                .message("Message processed successfully")
                .totalNotifications(successCount + failedCount)
                .successfulNotifications(successCount)
                .failedNotifications(failedCount)
                .build();
    }

    public List<NotificationLogResponse> getLogs() {
        return notificationLogRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private NotificationStatus sendNotificationAndSaveLog(
            User user,
            NotificationChannel channel,
            MessageRequest request
    ) {
        NotificationStatus status = NotificationStatus.SENT;
        String errorMessage = null;

        try {
            notificationDispatcher.getSender(channel).send(user, request);
        } catch (Exception ex) {
            status = NotificationStatus.FAILED;
            errorMessage = ex.getMessage();
        }

        NotificationLog log = NotificationLog.builder()
                .messageCategory(request.getCategory())
                .messageBody(request.getMessage())
                .notificationChannel(channel)
                .userId(user.getId())
                .userName(user.getName())
                .userEmail(user.getEmail())
                .userPhoneNumber(user.getPhoneNumber())
                .status(status)
                .errorMessage(errorMessage)
                .createdAt(LocalDateTime.now())
                .build();

        notificationLogRepository.save(log);

        return status;
    }

    private NotificationLogResponse mapToResponse(NotificationLog log) {
        return NotificationLogResponse.builder()
                .id(log.getId())
                .messageCategory(log.getMessageCategory())
                .messageBody(log.getMessageBody())
                .notificationChannel(log.getNotificationChannel())
                .userId(log.getUserId())
                .userName(log.getUserName())
                .userEmail(log.getUserEmail())
                .userPhoneNumber(log.getUserPhoneNumber())
                .status(log.getStatus())
                .errorMessage(log.getErrorMessage())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
