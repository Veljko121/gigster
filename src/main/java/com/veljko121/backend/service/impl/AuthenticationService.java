package com.veljko121.backend.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.veljko121.backend.core.enums.Role;
import com.veljko121.backend.dto.CredentialsDTO;
import com.veljko121.backend.model.User;
import com.veljko121.backend.service.IAuthenticationService;
import com.veljko121.backend.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final IUserService userService;

    private final AuthenticationManager authenticationManager;

    @Override
    public User register(User user) {
        user.setRole(Role.USER);
        return userService.save(user);
    }

    @Override
    public User login(CredentialsDTO credentialsDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword())
        );
        return userService.findByUsername(credentialsDTO.getUsername());
    }

    @Override
    public Boolean usernameExists(String username) {
        return userService.existsByUsername(username);
    }

    @Override
    public Boolean emailExists(String email) {
        return userService.existsByEmail(email);
    }
    
}
