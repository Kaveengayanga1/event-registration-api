package com.event_registration.lk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "UUID")//temporarily generate id
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String orderId;
    private String userId;
    private String eventId;
    private Integer ticketNumber;
    private LocalDateTime orderDate;
    private String orderStatus; //confirm or canceled

}
