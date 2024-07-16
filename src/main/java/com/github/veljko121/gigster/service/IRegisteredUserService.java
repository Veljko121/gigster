package com.github.veljko121.gigster.service;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.model.RegisteredUser;

public interface IRegisteredUserService extends ICRUDService<RegisteredUser, RegisterRequestDTO, RegisteredUserResponseDTO, Integer> {

    RegisteredUser getLoggedInRegisteredUser();
    
}
