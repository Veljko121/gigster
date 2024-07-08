package com.github.veljko121.gigster.service.impl;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.dto.UserRequestDTO;
import com.github.veljko121.gigster.dto.UserResponseDTO;
import com.github.veljko121.gigster.model.User; 
import com.github.veljko121.gigster.repository.UserRepository;
import com.github.veljko121.gigster.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO findByUsername(String username) throws NoSuchElementException {
        var user = userRepository.findByUsername(username).orElseThrow();
        var responseDTO = modelMapper.map(user, UserResponseDTO.class);
        return responseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var userResponse = userRepository.findByUsername(username).orElseThrow();
        var userDetails = modelMapper.map(userResponse, UserDetails.class);
        return userDetails;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO requestDTO) {
        var user = modelMapper.map(requestDTO, User.class);
        var savedUser = userRepository.save(user);
        var responseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return responseDTO;
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
