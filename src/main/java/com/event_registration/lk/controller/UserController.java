package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
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
    public Map<String,String> addUser(@RequestBody User user){
        Boolean result = userService.addUser(user);
        if (result)
            return Collections.singletonMap("signup status","successful");
        else
            return Collections.singletonMap("signup status","unsuccessful");
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
}
