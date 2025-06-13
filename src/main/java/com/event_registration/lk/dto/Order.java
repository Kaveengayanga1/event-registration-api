package com.event_registration.lk.dto;

import java.time.LocalDateTime;

public class Order {

    private String orderId;
    private String userId;
    private String eventId;
    private Integer ticketNumber;
    private LocalDateTime orderDate;
    private String orderStatus; //confirm or cancelled


}
