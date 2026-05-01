package com.gila.notifications.gilanotificationsystem.notification;

import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.exception.NotificationSenderNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NotificationDispatcher {

    private final Map<NotificationChannel, NotificationSender> senders;

    public NotificationDispatcher(List<NotificationSender> notificationSenders) {
        this.senders = notificationSenders.stream()
                .collect(Collectors.toMap(
                        NotificationSender::getChannel,
                        Function.identity()
                ));
    }

    public NotificationSender getSender(NotificationChannel channel) {
        NotificationSender sender = senders.get(channel);

        if (sender == null) {
            throw new NotificationSenderNotFoundException("No notification sender found for channel: " + channel);
        }

        return sender;
    }
}
