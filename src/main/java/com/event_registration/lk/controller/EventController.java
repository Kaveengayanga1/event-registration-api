package com.event_registration.lk.controller;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.response.EventResponse;
import com.event_registration.lk.service.EventService;
import com.sun.jdi.request.EventRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //Done
    @PostMapping("/add")
    public EventResponse addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }
    @DeleteMapping("/delete")
    public EventResponse deleteEvent(@RequestParam String eventId) {
        return new EventResponse("Status","not implemented yet");
    }
    @GetMapping("/get-all")
    public EventResponse getAllEvents() {
        //return new EventResponse("status","event list",eventService.getAllEvents());
        return new EventResponse("status","not implemented yet");
    }
    @PutMapping("/update")
    public EventResponse updateEvent(@RequestBody Event event) {
        return new EventResponse("Status","not implemented yet");
    }

}
