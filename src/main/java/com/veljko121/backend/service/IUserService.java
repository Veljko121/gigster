package com.veljko121.backend.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.veljko121.backend.model.User;

public interface IUserService extends UserDetailsService {

    User findByUsername(String username) throws NoSuchElementException;

    User save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
}
