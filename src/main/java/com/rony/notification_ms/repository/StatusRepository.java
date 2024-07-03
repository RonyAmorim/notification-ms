package com.rony.notification_ms.repository;

import com.rony.notification_ms.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
