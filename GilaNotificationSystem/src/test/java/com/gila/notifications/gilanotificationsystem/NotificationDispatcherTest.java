package com.gila.notifications.gilanotificationsystem;

import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.exception.NotificationSenderNotFoundException;
import com.gila.notifications.gilanotificationsystem.notification.NotificationDispatcher;
import com.gila.notifications.gilanotificationsystem.notification.NotificationSender;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationDispatcherTest {

    @Test
    void shouldReturnCorrectSenderByChannel() {
        NotificationSender emailSender = mock(NotificationSender.class);
        when(emailSender.getChannel()).thenReturn(NotificationChannel.EMAIL);

        NotificationDispatcher dispatcher =
                new NotificationDispatcher(List.of(emailSender));

        NotificationSender result = dispatcher.getSender(NotificationChannel.EMAIL);

        assertSame(emailSender, result);
    }

    @Test
    void shouldThrowExceptionWhenSenderDoesNotExist() {
        NotificationSender emailSender = mock(NotificationSender.class);
        when(emailSender.getChannel()).thenReturn(NotificationChannel.EMAIL);

        NotificationDispatcher dispatcher =
                new NotificationDispatcher(List.of(emailSender));

        assertThrows(
                NotificationSenderNotFoundException.class,
                () -> dispatcher.getSender(NotificationChannel.SMS)
        );
    }
}
