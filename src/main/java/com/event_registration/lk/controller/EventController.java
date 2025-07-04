package com.event_registration.lk.controller;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.response.EventResponse;
import com.event_registration.lk.service.EventService;
import org.springframework.web.bind.annotation.*;
//Done
@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
    //Done
    @DeleteMapping("/delete")
    public EventResponse deleteEvent(@RequestParam String eventId) {
        return eventService.removeEvent(eventId);
    }
    //Done
    @GetMapping("/get-all-events")
    public EventResponse getAllEvents() {
        return eventService.getAllEvents();
    }
    //Done
    @PutMapping("/update")
    public EventResponse updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

}
