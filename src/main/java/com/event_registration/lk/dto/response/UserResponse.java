package com.event_registration.lk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String status;
    private String message;
    private String jwtToken;

    public UserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
