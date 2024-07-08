package com.github.veljko121.gigster.service;

import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;

public interface IAuthenticationService {
    
    String register(RegisterRequestDTO requestDTO);

    String login(CredentialsDTO credentialsDTO);

    Boolean usernameExists(String username);

    Boolean emailExists(String email);

}
