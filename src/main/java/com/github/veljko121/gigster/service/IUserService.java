package com.github.veljko121.gigster.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.github.veljko121.gigster.model.User;

public interface IUserService extends UserDetailsService {

    User findByUsername(String username) throws NoSuchElementException;

    User save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
}
