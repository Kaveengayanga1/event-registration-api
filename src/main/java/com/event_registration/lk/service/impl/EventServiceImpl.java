package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.service.EventService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary //main service for event handling
public class EventServiceImpl implements EventService {


    @Override
    public Boolean addEvent(Event event) {
        return null;
    }

    @Override
    public Boolean removeEvent(Event event) {
        return null;
    }

    @Override
    public Boolean updateEvent(Event event) {
        return null;
    }

    @Override
    public Event getEvent(String keyword) {
        return null;
    }

    @Override
    public ArrayList<Event> getAllEvents() {
        return null;
    }
}
