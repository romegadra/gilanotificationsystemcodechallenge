package com.gila.notifications.gilanotificationsystem.notification;

import com.gila.notifications.gilanotificationsystem.dto.MessageRequest;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.mock.User;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender implements NotificationSender {

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(User user, MessageRequest request) {
        System.out.println("Sending EMAIL to " + user.getEmail() + ": " + request.getMessage());
    }
}
