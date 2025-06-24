package com.event_registration.lk.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Event {
    private String eventId;
    private String name;
    private String description;
    //private ArrayList<PriceRange> priceRanges;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceRange> priceRanges; // ✅ Use List instead of ArrayList
    private ArrayList<LocalDateTime> dates;
    private String location;
    private byte[] image;

}
