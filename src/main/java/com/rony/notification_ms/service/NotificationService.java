package com.rony.notification_ms.service;

import com.rony.notification_ms.dto.ScheduleNotificationDTO;
import com.rony.notification_ms.entity.Notification;
import com.rony.notification_ms.entity.Status;
import com.rony.notification_ms.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
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

    public void checkAndSendNotification(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()),
                dateTime);

        notifications.forEach(SendNotification());
        }

    private Consumer<Notification> SendNotification() {
        return notification -> {
            String channelDescription = notification.getChannel().getDescription().toUpperCase();
            logger.info("Sending notification ID: {} via channel: {}", notification.getId(), channelDescription);

            switch (channelDescription) {
                case "EMAIL":
                    emailService.sendEmail(notification.getDestination(), "Teste", notification.getMessage());
                    break;
                case "SMS":
                    // smsService.sendSms(notification.getDestination(), notification.getMessage());
                    break;
                default:
                    logger.error("Channel not supported: {}", channelDescription);
                    throw new RuntimeException("Channel not supported: " + channelDescription);
            }

            notification.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(notification);
        };
    }
}
