package com.event_registration.lk.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private String status;
    private String message;
}
