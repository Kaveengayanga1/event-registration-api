package com.event_registration.lk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderEntity {

    @Id
    private String orderId;
    private String userId;
    private String eventId;
    private Integer ticketNumber;
    private LocalDateTime orderDate;
    private String orderStatus; //confirm or cancelled

}
