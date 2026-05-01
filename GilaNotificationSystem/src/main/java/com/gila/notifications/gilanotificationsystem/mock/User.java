package com.gila.notifications.gilanotificationsystem.mock;

import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<MessageCategory> subscribed;
    private List<NotificationChannel> channels;
}
