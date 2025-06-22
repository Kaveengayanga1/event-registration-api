package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.BookingOrder;
import com.event_registration.lk.dto.request.BookingRequest;
import com.event_registration.lk.dto.response.BookingResponse;
import com.event_registration.lk.entity.BookingOrderEntity;
import com.event_registration.lk.repository.BookingRepository;
import com.event_registration.lk.repository.EventRepository;
import com.event_registration.lk.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//Done
@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    EventRepository eventRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }
    //Done
    @Override
    public BookingResponse bookEvent(BookingRequest bookingRequest) {
        try {
            bookingRepository.save(
                    BookingOrderEntity.builder()
                            .bookingId(generateBookingId())
                            .eventId(bookingRequest.getEventId())
                            .userId(bookingRequest.getUserId())
                            .orderedDate(bookingRequest.getLocalDateTime())
                            .ticketNumber(generateTicketNumber())
                            .orderStatus("confirmed")
                            .build()
            );
            return BookingResponse.builder()
                    .status("booking")
                    .message("success")
                    .build();
        } catch (Exception e) {
            log.info("error in booking event : "+e.getMessage());
            return BookingResponse.builder()
                    .status("booking")
                    .message("unsuccess : "+e.getMessage())
                    .build();
        }
    }
    //Done
    @Override
    public BookingResponse cancelEvent(String bookingId) {
        try{
            if(!bookingRepository.existsByBookingIdContainingIgnoreCase(bookingId)){
                return BookingResponse.builder()
                        .status("booking-cancel")
                        .message("no record exists for booking id : " + bookingId + "")
                        .build();
            }
            boolean result = bookingRepository.deleteByBookingIdContainingIgnoreCase(bookingId);
            if (result){
                return BookingResponse.builder()
                        .status("booking-cancel")
                        .message("success")
                        .build();
            }else {
                return BookingResponse.builder()
                        .status("booking-cancel")
                        .message("unsuccess")
                        .build();
            }
        }catch (Exception e){
            return BookingResponse.builder()
                    .status("booking-cancel")
                    .message("error occurred : "+e.getMessage())
                    .build();
        }
    }
    //Done
    @Override
    public BookingResponse getUserBookingDetails(Long userId) {
        List<BookingOrderEntity> allByUserId = bookingRepository.findAllByUserId(userId);
        if (allByUserId.isEmpty()){
            return BookingResponse.builder()
                    .status("booking-details")
                    .message("no record exists for user id : " + userId)
                    .build();
        }else {
            List<BookingOrder> collect = allByUserId.stream().map(this::toBookingOrder).collect(Collectors.toList());
            return BookingResponse.builder()
                    .status("booking-details")
                    .message("record found for user id : " + userId)
                    .orderList((ArrayList<BookingOrder>) collect)
                    .build();
        }
    }

    private BookingOrder toBookingOrder(BookingOrderEntity entity){
        return BookingOrder.builder()
                .bookingId(entity.getBookingId())
                .eventId(entity.getEventId())
                .ticketNumber(entity.getTicketNumber())
                .orderDate(entity.getOrderedDate())
                .userId(entity.getUserId())
                .orderStatus(entity.getOrderStatus())
                .build();
    }

    private String generateTicketNumber(){
        LocalDateTime dateTime = LocalDateTime.now();

        String date = dateTime.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String time = dateTime.format(DateTimeFormatter.ofPattern("ssmmHH"));

        long todayCount = bookingRepository.countByOrderedDateBetween(
                dateTime.toLocalDate().atStartOfDay(),
                dateTime.toLocalDate().plusDays(1).atStartOfDay()
        );
        String countSeq = String.format("%05d", todayCount);
        return "TKT"+countSeq+time+date;
    }

    private String generateBookingId() {
        String prefix = "B";
        String uniquePart = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 9)
                .toUpperCase();
        return prefix + uniquePart;
    }
}
