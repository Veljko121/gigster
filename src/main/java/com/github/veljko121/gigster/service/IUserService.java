package com.github.veljko121.gigster.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.github.veljko121.gigster.dto.UserRequestDTO;
import com.github.veljko121.gigster.dto.UserResponseDTO;

public interface IUserService extends UserDetailsService {

    UserResponseDTO findByUsername(String username) throws NoSuchElementException;

    UserResponseDTO save(UserRequestDTO user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
}
