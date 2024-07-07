package com.github.veljko121.gigster.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.enums.Role;
import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.model.User;
import com.github.veljko121.gigster.service.IAuthenticationService;
import com.github.veljko121.gigster.service.IUserService;

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
            new UsernamePasswordAuthenticationToken(credentialsDTO.username(), credentialsDTO.password())
        );
        return userService.findByUsername(credentialsDTO.username());
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
