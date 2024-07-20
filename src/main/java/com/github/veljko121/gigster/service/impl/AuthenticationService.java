package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.enums.Role;
import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.dto.ChangePasswordRequestDTO;
import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.model.User;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.repository.UserRepository;
import com.github.veljko121.gigster.service.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    private final RegisteredUserRepository registeredUserRepository;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    private final IJwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequestDTO requestDTO) {
        var user = modelMapper.map(requestDTO, RegisteredUser.class);
        user.setRole(Role.REGISTERED_USER);
        setPassword(user, user.getPassword());
        var savedUser = registeredUserRepository.save(user);
        var jwt = jwtService.generateJwt(savedUser);
        return jwt;
    }

    @Override
    public String login(CredentialsDTO credentialsDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword())
        );
        var user = userRepository.findByUsername(credentialsDTO.getUsername()).orElseThrow();
        var jwt = jwtService.generateJwt(user);
        return jwt;
    }

    @Override
    public Boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void changePassword(ChangePasswordRequestDTO requestDTO) {
        var loggedInUser = userRepository.findById(jwtService.getLoggedInUserId()).orElseThrow();
        if (!passwordEncoder.matches(requestDTO.getOldPassword(), loggedInUser.getPassword())) throw new InternalAuthenticationServiceException("Old password did not match with an existing account.");
        setPassword(loggedInUser, requestDTO.getNewPassword());
        userRepository.save(loggedInUser);
    }

    private void setPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
    }
    
}
