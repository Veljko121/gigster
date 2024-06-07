package com.github.veljko121.backend.service;

import com.github.veljko121.backend.dto.CredentialsDTO;
import com.github.veljko121.backend.model.User;

public interface IAuthenticationService {
    
    User register(User user);

    User login(CredentialsDTO credentialsDTO);

    Boolean usernameExists(String username);

    Boolean emailExists(String email);

}
