package com.rony.notification_ms.repository;

import com.rony.notification_ms.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
