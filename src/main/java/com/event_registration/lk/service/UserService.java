package com.event_registration.lk.service;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;


public interface UserService {

    public UserResponse addUser(User user);
    public Boolean removeUser(String keyword);
    public Boolean updateUser(String keyword);
    public ArrayList<User> getAllUsers();

}
