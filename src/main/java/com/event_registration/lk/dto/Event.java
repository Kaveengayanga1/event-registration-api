package com.event_registration.lk.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {

    private String eventId;
    private String name;
    private String description;
    private ArrayList<PriceRange> priceRanges;
    private ArrayList<LocalDateTime> dates;
    private String location;
    private byte[] image;

}
