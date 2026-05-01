package com.gila.notifications.gilanotificationsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageResponse {

    private String message;
    private int totalNotifications;
    private int successfulNotifications;
    private int failedNotifications;
}
