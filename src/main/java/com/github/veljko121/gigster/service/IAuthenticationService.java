package com.github.veljko121.gigster.service;

import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.model.User;

public interface IAuthenticationService {
    
    User register(User user);

    User login(CredentialsDTO credentialsDTO);

    Boolean usernameExists(String username);

    Boolean emailExists(String email);

}
