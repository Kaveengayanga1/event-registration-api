package com.event_registration.lk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Event {
    private String eventId;
    private String name;
    private String description;
    private ArrayList<PriceRange> priceRanges;
    private ArrayList<LocalDateTime> dates;
    private String location;
    private byte[] image;

}
