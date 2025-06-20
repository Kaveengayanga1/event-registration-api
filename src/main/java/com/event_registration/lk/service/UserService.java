package com.event_registration.lk.service;

import com.event_registration.lk.dto.User;
import com.event_registration.lk.dto.response.UserResponse;

import java.util.ArrayList;

public interface UserService {
    public UserResponse addUser(User user);
    public UserResponse loginUser(User user);
    public Boolean removeUser(String keyword);
    public Boolean updateUser(String keyword);
    public ArrayList<User> getAllUsers();
    public User getUserByEmail(String email);
}
