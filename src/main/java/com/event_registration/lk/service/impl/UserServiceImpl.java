package com.event_registration.lk.service.impl;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.entity.UserEntity;
import com.event_registration.lk.repository.UserRepository;
import com.event_registration.lk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean addUser(User user) {

        log.info("Adding user process started");

        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        log.info("Process is completing..");
        return userRepository.save(userEntity) == null ? false : true ;
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
