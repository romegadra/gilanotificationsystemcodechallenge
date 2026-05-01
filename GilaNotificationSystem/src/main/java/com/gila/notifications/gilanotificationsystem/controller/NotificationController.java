package com.gila.notifications.gilanotificationsystem.controller;

import com.gila.notifications.gilanotificationsystem.dto.MessageRequest;
import com.gila.notifications.gilanotificationsystem.dto.NotificationLogResponse;
import com.gila.notifications.gilanotificationsystem.dto.SendMessageResponse;
import com.gila.notifications.gilanotificationsystem.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    private SendMessageResponse sendMessage(@Valid @RequestBody MessageRequest request) {
        return notificationService.sendMessage(request);
    }

    @GetMapping("/logs")
    public List<NotificationLogResponse> getLogs() {
        return notificationService.getLogs();
    }

}
