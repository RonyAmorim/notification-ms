package com.rony.notification_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_status")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    private Long id;

    private String description;
}
