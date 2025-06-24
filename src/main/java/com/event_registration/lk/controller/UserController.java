package com.event_registration.lk.controller;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete")
    public UserResponse deleteUser(@RequestParam String userId) {
        return userService.removeUser(Long.parseLong(userId));
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

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
