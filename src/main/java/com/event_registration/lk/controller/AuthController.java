package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponse signUpUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public UserResponse loginUser(@RequestBody User user){
        log.info("login user control layer");
        return userService.loginUser(user);
    }
    @GetMapping
    public String hello(){
        return "hello auth";
    }



}
