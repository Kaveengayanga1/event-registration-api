package com.event_registration.lk.dto.request;

import com.event_registration.lk.dto.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingRequest {
    private String eventId;
    private Long userId;
    private LocalDateTime localDateTime;
}
