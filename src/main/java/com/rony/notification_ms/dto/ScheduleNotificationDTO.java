package com.rony.notification_ms.dto;

import com.rony.notification_ms.entity.Channel;
import com.rony.notification_ms.entity.Notification;
import com.rony.notification_ms.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDTO(
    LocalDateTime dateTime,
    String destination,
    String message,
    Channel.Values channel
) {
    public Notification toNotification() {
        return new Notification(
            dateTime,
            destination,
            message,
            channel.toChannel(),
            Status.Values.PENDING.toStatus()
        );
    }
}
