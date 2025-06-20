package com.event_registration.lk.controller;

import com.event_registration.lk.dto.Event;
import com.event_registration.lk.dto.response.EventResponse;
import com.sun.jdi.request.EventRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    @PostMapping("/add")
    public EventResponse addEvent(@RequestBody Event event) {
        //TODO
        return new EventResponse("Status","not implemented yet");
    }
    @DeleteMapping("/delete")
    public EventResponse deleteEvent(@RequestParam String eventId) {
        return new EventResponse("Status","not implemented yet");
    }
    @GetMapping("/get-all")
    public EventResponse getAllEvents() {
        return new EventResponse("Status",new ArrayList<Event>());
    }
    @PutMapping("/update")
    public EventResponse updateEvent(@RequestBody Event event) {
        return new EventResponse("Status","not implemented yet");
    }

}
