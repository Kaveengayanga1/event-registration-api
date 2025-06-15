package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserResponse addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
}
