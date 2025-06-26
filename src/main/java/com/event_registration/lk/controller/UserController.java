package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
//Done
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete")
    public UserResponse deleteUser(@RequestParam Long userId) {
        return userService.removeUser(userId);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    //for testing purpose
    @GetMapping("/hello")
    public String sayHello(){
        Date date = new Date(new java.util.Date().getTime());
        return "Hello "+date;
    }

    @GetMapping("/get-all-users")
    public UserResponse getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/get-user-by-email")
    public UserResponse getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

}
