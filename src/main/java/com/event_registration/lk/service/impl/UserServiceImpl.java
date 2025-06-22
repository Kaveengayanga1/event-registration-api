package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.request.LoginRequest;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.entity.UserEntity;
import com.event_registration.lk.repository.UserRepository;
import com.event_registration.lk.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ObjectMapper objectMapper;
    AuthenticationManager authenticationManager;
    JwtServiceImpl jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = new ObjectMapper();
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse addUser(User user) {

        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        if (userRepository.existsByEmailContainingIgnoreCase(user.getEmail())) {
            return new UserResponse("signup", "email already in use");
        }
        try {
            userRepository.save(userEntity);

            //send email using producer
            //signupEventProducer.publishUserSignupEvent(userEntity.getEmail());

            return new UserResponse("signup","success");
        } catch (Exception e) {
            return new UserResponse("signup","unsuccess");
        }
    }

    @Override
    public UserResponse loginUser(LoginRequest loginRequest) {
        Optional<UserEntity> entity = userRepository.findByEmailIgnoreCase(loginRequest.getEmail());
        String jwtToken = jwtService.generateJwtToken(loginRequest);
        log.info("login user service layer");
        Authentication authentication = authenticationManager.authenticate(

                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail() /*!= null ? user.getEmail() : user.getUsername()*/,
                        loginRequest.getPassword()
                )
        );
        if (authentication.isAuthenticated()){
            return new UserResponse("login","success",entity.get().getRole(), jwtToken);
        }else {
            return new UserResponse("login", "unsuccess");
        }
    }

    @Override
    public Boolean removeUser(String keyword) {
        return null;
    }

    @Override
    public Boolean updateUser(String keyword) {
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>();
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntityByEmailContainingIgnoreCase = userRepository.findUserEntityByEmailContainingIgnoreCase(email);
        return objectMapper.convertValue(userEntityByEmailContainingIgnoreCase.get(), User.class);
    }
}
