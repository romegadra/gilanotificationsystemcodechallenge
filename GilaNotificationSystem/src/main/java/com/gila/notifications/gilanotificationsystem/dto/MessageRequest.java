package com.gila.notifications.gilanotificationsystem.dto;

import com.gila.notifications.gilanotificationsystem.enums.MessageCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.awt.*;

@Data
public class MessageRequest {

    @NotNull( message ="Category is required")
    private MessageCategory category;

    @NotBlank(message = "Message body is required")
    private String message;
}
