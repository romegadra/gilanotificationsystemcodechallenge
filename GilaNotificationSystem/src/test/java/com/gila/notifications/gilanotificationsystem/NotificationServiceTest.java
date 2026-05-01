package com.gila.notifications.gilanotificationsystem;

import com.gila.notifications.gilanotificationsystem.dto.MessageRequest;
import com.gila.notifications.gilanotificationsystem.dto.SendMessageResponse;
import com.gila.notifications.gilanotificationsystem.entity.NotificationLog;
import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.enums.NotificationStatus;
import com.gila.notifications.gilanotificationsystem.mock.User;
import com.gila.notifications.gilanotificationsystem.notification.NotificationDispatcher;
import com.gila.notifications.gilanotificationsystem.notification.NotificationSender;
import com.gila.notifications.gilanotificationsystem.repository.NotificationLogRepository;
import com.gila.notifications.gilanotificationsystem.repository.UserMockRepository;
import com.gila.notifications.gilanotificationsystem.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private UserMockRepository userMockRepository;

    @Mock
    private NotificationDispatcher notificationDispatcher;

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @Mock
    private NotificationSender notificationSender;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldSendNotificationsOnlyToUsersSubscribedToCategory() {
        MessageRequest request = new MessageRequest();
        request.setCategory(MessageCategory.SPORTS);
        request.setMessage("Sports message");

        User sportsUser = new User(
                1L,
                "John",
                "john@email.com",
                "5551112222",
                List.of(MessageCategory.SPORTS),
                List.of(NotificationChannel.EMAIL, NotificationChannel.PUSH_NOTIFICATION)
        );

        User financeUser = new User(
                2L,
                "Jane",
                "jane@email.com",
                "5553334444",
                List.of(MessageCategory.FINANCE),
                List.of(NotificationChannel.EMAIL)
        );

        when(userMockRepository.findAll()).thenReturn(List.of(sportsUser, financeUser));
        when(notificationDispatcher.getSender(any(NotificationChannel.class))).thenReturn(notificationSender);

        SendMessageResponse response = notificationService.sendMessage(request);

        assertEquals(2, response.getTotalNotifications());
        assertEquals(2, response.getSuccessfulNotifications());
        assertEquals(0, response.getFailedNotifications());

        verify(notificationDispatcher, times(2)).getSender(any(NotificationChannel.class));
        verify(notificationSender, times(2)).send(eq(sportsUser), eq(request));
        verify(notificationSender, never()).send(eq(financeUser), eq(request));
        verify(notificationLogRepository, times(2)).save(any(NotificationLog.class));
    }

    @Test
    void shouldSaveFailedLogWhenSenderThrowsException() {
        MessageRequest request = new MessageRequest();
        request.setCategory(MessageCategory.SPORTS);
        request.setMessage("Sports message");

        User user = new User(
                1L,
                "John",
                "john@email.com",
                "5551112222",
                List.of(MessageCategory.SPORTS),
                List.of(NotificationChannel.EMAIL)
        );

        when(userMockRepository.findAll()).thenReturn(List.of(user));
        when(notificationDispatcher.getSender(NotificationChannel.EMAIL)).thenReturn(notificationSender);
        doThrow(new RuntimeException("Email provider failed"))
                .when(notificationSender).send(user, request);

        SendMessageResponse response = notificationService.sendMessage(request);

        assertEquals(1, response.getTotalNotifications());
        assertEquals(0, response.getSuccessfulNotifications());
        assertEquals(1, response.getFailedNotifications());

        ArgumentCaptor<NotificationLog> captor =
                ArgumentCaptor.forClass(NotificationLog.class);

        verify(notificationLogRepository).save(captor.capture());

        NotificationLog savedLog = captor.getValue();

        assertEquals(NotificationStatus.FAILED, savedLog.getStatus());
        assertEquals("Email provider failed", savedLog.getErrorMessage());
        assertEquals(NotificationChannel.EMAIL, savedLog.getNotificationChannel());
        assertEquals(MessageCategory.SPORTS, savedLog.getMessageCategory());
    }

    @Test
    void shouldReturnZeroNotificationsWhenNoUsersAreSubscribed() {
        MessageRequest request = new MessageRequest();
        request.setCategory(MessageCategory.MOVIES);
        request.setMessage("Movie message");

        User user = new User(
                1L,
                "John",
                "john@email.com",
                "5551112222",
                List.of(MessageCategory.SPORTS),
                List.of(NotificationChannel.EMAIL)
        );

        when(userMockRepository.findAll()).thenReturn(List.of(user));

        SendMessageResponse response = notificationService.sendMessage(request);

        assertEquals(0, response.getTotalNotifications());
        assertEquals(0, response.getSuccessfulNotifications());
        assertEquals(0, response.getFailedNotifications());

        verify(notificationDispatcher, never()).getSender(any());
        verify(notificationSender, never()).send(any(), any());
        verify(notificationLogRepository, never()).save(any());
    }
}
