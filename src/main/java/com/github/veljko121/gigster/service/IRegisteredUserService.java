package com.github.veljko121.gigster.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserUpdateRequestDTO;
import com.github.veljko121.gigster.model.RegisteredUser;

public interface IRegisteredUserService extends ICRUDService<RegisteredUser, RegisterRequestDTO, RegisteredUserResponseDTO, RegisteredUserUpdateRequestDTO, Integer> {

    RegisteredUserResponseDTO getLoggedInRegisteredUser();

    AuthenticationResponseDTO updateLoggedInRegisteredUserProfile(RegisteredUserUpdateRequestDTO requestDTO);

    void updateProfilePicture(MultipartFile file) throws IOException, InterruptedException;
    
}
