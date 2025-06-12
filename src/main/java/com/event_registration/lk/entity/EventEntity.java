package com.event_registration.lk.entity;

import com.event_registration.lk.dto.PriceRange;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventId;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "event_price_ranges",
            joinColumns = @JoinColumn(name = "event_id")
    )
    private ArrayList<PriceRange> priceRanges;

}
