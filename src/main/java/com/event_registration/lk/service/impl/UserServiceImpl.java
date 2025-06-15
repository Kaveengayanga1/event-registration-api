package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import com.event_registration.lk.entity.UserEntity;
import com.event_registration.lk.repository.UserRepository;
import com.event_registration.lk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse addUser(User user) {

        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        if (userRepository.existsUserEntityByEmailContainingIgnoreCase(user.getEmail())){
            return new UserResponse("409","email already in use");
        }
        try {
            userRepository.save(userEntity);
            return new UserResponse("signup","success");
        } catch (Exception e) {
            return new UserResponse("signup","unsuccess");
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
        return null;
    }
}
