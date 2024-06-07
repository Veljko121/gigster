package com.veljko121.backend.service.impl;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.veljko121.backend.core.exception.EmailNotUniqueException;
import com.veljko121.backend.core.exception.UsernameNotUniqueException;
import com.veljko121.backend.model.User;
import com.veljko121.backend.repository.UserRepository;
import com.veljko121.backend.service.IUserService;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) throws NoSuchElementException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    public User save(User user) {
        if (existsByUsername(user.getUsername())) throw new UsernameNotUniqueException(user.getUsername());
        if (existsByEmail(user.getEmail())) throw new EmailNotUniqueException(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
}
