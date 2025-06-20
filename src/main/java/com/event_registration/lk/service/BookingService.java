package com.event_registration.lk.service;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.request.BookingRequest;
import com.event_registration.lk.dto.response.BookingResponse;

public interface BookingService {

    public BookingResponse bookEvent(BookingRequest bookingRequest);
    public BookingResponse cancelEvent(String eventId, String userId);
    public BookingResponse confirmEvent(String eventId, String userId);
    public BookingResponse getBooking(String eventId, String userId);
}
