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

    public Status(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public enum Values{
        PENDING(1, "Pending"),
        SUCCESS(2, "Success"),
        ERROR(3, "Error"),
        CANCELED(4, "Canceled");

        private long id;
        private String description;

        Values(long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Status toStatus(){
            return new Status(id, description);
        }
    }
}
