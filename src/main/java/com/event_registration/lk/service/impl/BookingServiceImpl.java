package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.request.BookingRequest;
import com.event_registration.lk.dto.response.BookingResponse;
import com.event_registration.lk.entity.EventEntity;
import com.event_registration.lk.entity.OrderEntity;
import com.event_registration.lk.repository.BookingRepository;
import com.event_registration.lk.repository.EventRepository;
import com.event_registration.lk.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    EventRepository eventRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public BookingResponse bookEvent(BookingRequest bookingRequest) {
        try {
            bookingRepository.save(
                    OrderEntity.builder()
                            .eventId(bookingRequest.getEvent().getEventId())
                            .orderDate(bookingRequest.getLocalDateTime())
                            .orderStatus("confirmed")
                            .build()
            );
            return BookingResponse.builder()
                    .status("booking")
                    .message("unsuccess")
                    .build();
        } catch (Exception e) {
            return BookingResponse.builder()
                    .status("booking")
                    .message("unsuccess")
                    .build();
        }
    }

    @Override
    public BookingResponse cancelEvent(String eventId, String userId) {
        return null;
    }

    @Override
    public BookingResponse confirmEvent(String eventId, String userId) {
        return null;
    }

    @Override
    public BookingResponse getBooking(String eventId, String userId) {
        return null;
    }
}
