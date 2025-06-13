package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @GetMapping("/add")
    public Map<String,String> addUser(@RequestBody User user){
        return Map.of("message","success");
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
}
