package com.event_registration.lk.service;

import com.event_registration.lk.dto.Event;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface EventService {

    public Boolean addEvent(Event event);
    public Boolean removeEvent(Event event);
    public Boolean updateEvent(Event event);
    public Event getEvent(String keyword);
    public ArrayList<Event> getAllEvents();

}
