//package com.event_registration.lk.service.impl;
//
//import com.event_registration.lk.entity.UserEntity;
//import com.event_registration.lk.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailServiceImpl implements UserDetailsService {
//
//    UserRepository userRepository;
//
//    public CustomUserDetailServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEntity> user = userRepository.findUserEntityByEmailContainingIgnoreCase(username);
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.get().getEmail())
//                .password(user.get().getPassword())
//                .roles(user.get().getRole().name())
//                .build();
//    }
//}
