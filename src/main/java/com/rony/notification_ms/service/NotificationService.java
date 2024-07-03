package com.rony.notification_ms.service;

import com.rony.notification_ms.dto.ScheduleNotificationDTO;
import com.rony.notification_ms.entity.Notification;
import com.rony.notification_ms.entity.Status;
import com.rony.notification_ms.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDTO dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        var notification = findById(notificationId);
        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }
}
