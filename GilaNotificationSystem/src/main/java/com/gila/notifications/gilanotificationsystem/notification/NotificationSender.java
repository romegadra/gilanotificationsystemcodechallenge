package com.gila.notifications.gilanotificationsystem.notification;

import com.gila.notifications.gilanotificationsystem.dto.MessageRequest;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.mock.User;

public interface NotificationSender {

    NotificationChannel getChannel();

    void send(User user, MessageRequest request);
}
