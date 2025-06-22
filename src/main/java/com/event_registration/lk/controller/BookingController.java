package com.event_registration.lk.controller;

import com.event_registration.lk.dto.request.BookingRequest;
import com.event_registration.lk.dto.response.BookingResponse;
import com.event_registration.lk.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponse bookEvent(@RequestBody BookingRequest bookingRequest){
        return bookingService.bookEvent(bookingRequest);
    }
    @GetMapping
    public BookingResponse getUserBookingDetails(@RequestParam Long userId){
        return bookingService.getUserBookingDetails(userId);
    }
    @DeleteMapping
    public BookingResponse cancelEvent(@RequestParam String bookingId){
        return bookingService.cancelEvent(bookingId);
    }
}
