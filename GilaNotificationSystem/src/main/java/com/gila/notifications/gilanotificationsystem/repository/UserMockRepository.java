package com.gila.notifications.gilanotificationsystem.repository;

import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import com.gila.notifications.gilanotificationsystem.enums.NotificationChannel;
import com.gila.notifications.gilanotificationsystem.mock.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMockRepository {

    public List<User> findAll() {
        return List.of(
                new User(
                        1L,
                        "John Doe",
                        "john.doe@email.com",
                        "5551112222",
                        List.of(MessageCategory.SPORTS, MessageCategory.MOVIES),
                        List.of(NotificationChannel.EMAIL, NotificationChannel.PUSH_NOTIFICATION)
                ),
                new User(
                        2L,
                        "Jane Smith",
                        "jane.smith@email.com",
                        "5553334444",
                        List.of(MessageCategory.FINANCE),
                        List.of(NotificationChannel.SMS, NotificationChannel.EMAIL)
                ),
                new User(
                        3L,
                        "Mike Brown",
                        "mike.brown@email.com",
                        "5557778888",
                        List.of(MessageCategory.SPORTS, MessageCategory.FINANCE),
                        List.of(NotificationChannel.PUSH_NOTIFICATION)
                )
        );
    }
}
