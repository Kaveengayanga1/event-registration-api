package com.event_registration.lk.service;

import com.event_registration.lk.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface UserService {

    public Boolean addUser(User user);
    public Boolean removeUser(String keyword);
    public Boolean updateUser(String keyword);
    public ArrayList<User> getAllUsers();

}
