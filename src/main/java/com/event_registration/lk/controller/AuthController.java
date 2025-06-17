package com.event_registration.lk.controller;

import com.event_registration.lk.dto.LoginRequest;
import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.AuthResponse;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    AuthenticationManager authenticationManager;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody User user) {
        UserResponse response = userService.addUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                        )
        );
        userService.
    }
}
