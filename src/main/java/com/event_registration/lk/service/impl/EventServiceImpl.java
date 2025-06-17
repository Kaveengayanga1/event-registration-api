package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.response.EventResponse;
import com.event_registration.lk.entity.EventEntity;
import com.event_registration.lk.repository.EventRepository;
import com.event_registration.lk.service.EventService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary //main service for event handling
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponse addEvent(Event event) {
        try{
            eventRepository.save(
                    EventEntity.builder()
                    .eventId(event.getEventId())
                    .name(event.getName())
                    .description(event.getDescription())
                    .dates(event.getDates())
                    .location(event.getLocation())
                    .image(event.getImage())
                    .priceRanges(event.getPriceRanges())
                    .build()
            );
            return new EventResponse("event-add","success");
        }catch (Exception e){
            return new EventResponse("event-add","event already exists");
        }
    }

    @Override
    public EventResponse removeEvent(String id) {
        Boolean result = eventRepository.existsEventEntityByEventIdContainingIgnoreCase(id);
        if (result == true){
            eventRepository.delete(eventRepository.findEventEntityByEventIdContainingIgnoreCase(id));
            return new EventResponse("event-remove","success");
        }
        return new EventResponse("event-remove","event not exists");

    }

    @Override
    public EventResponse updateEvent(Event event) {
        Boolean result = eventRepository.existsEventEntityByEventIdContainingIgnoreCase(event.getEventId());
        if (result == true){
            eventRepository.delete(eventRepository.findEventEntityByEventIdContainingIgnoreCase(event.getEventId()));
            eventRepository.save(
                    EventEntity.builder()
                            .eventId(event.getEventId())
                            .name(event.getName())
                            .description(event.getDescription())
                            .dates(event.getDates())
                            .location(event.getLocation())
                            .image(event.getImage())
                            .priceRanges(event.getPriceRanges())
                            .build()
            );
            return new EventResponse("event-update","success");
        }
        return new EventResponse("event-update","event not exists");
    }

    @Override
    public EventResponse getEvent(String id) {
        return null;
    }

    @Override
    public ArrayList<Event> getAllEvents() {
        return null;
    }
}
