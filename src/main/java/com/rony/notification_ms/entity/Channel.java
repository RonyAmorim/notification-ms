package com.rony.notification_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_channel")
@Getter
@Setter
@NoArgsConstructor
public class Channel {
    @Id
    private Long id;

    private String description;

    public Channel(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public enum Values{
        EMAIL(1, "Email"),
        SMS(2, "SMS"),
        PUSH(3, "Push"),
        WHATSAPP(4, "WhatsApp");

        private long id;
        private String description;

        Values(long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Channel toChannel(){
            return new Channel(id, description);
        }
    }
}
