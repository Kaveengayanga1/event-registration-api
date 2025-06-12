package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @GetMapping("/hello")
    public String addUser(@RequestBody User user){
        return "Hello";
    }
}
