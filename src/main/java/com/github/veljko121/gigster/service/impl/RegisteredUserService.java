package com.github.veljko121.gigster.service.impl;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserUpdateRequestDTO;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.service.IRegisteredUserService;
import com.github.veljko121.gigster.storage.impl.ProfilePictureStorage;

@Service
public class RegisteredUserService extends CRUDService<RegisteredUser, RegisterRequestDTO, RegisteredUserResponseDTO, RegisteredUserUpdateRequestDTO, Integer> implements IRegisteredUserService {

    private final ModelMapper modelMapper;

    private final RegisteredUserRepository registeredUserRepository;

    private final ProfilePictureStorage profilePictureStorage;

    private final IJwtService jwtService;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, ProfilePictureStorage profilePictureStorage, IJwtService jwtService, ModelMapper modelMapper) {
        super(registeredUserRepository);
        this.registeredUserRepository = registeredUserRepository;
        this.profilePictureStorage = profilePictureStorage;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected RegisteredUserResponseDTO mapToResponseDTO(RegisteredUser entity) {
        return modelMapper.map(entity, RegisteredUserResponseDTO.class);
    }

    @Override
    protected RegisteredUser mapToDomain(RegisterRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, RegisteredUser.class);
    }

    @Override
    public RegisteredUserResponseDTO getLoggedInRegisteredUser() {
        return mapToResponseDTO(getLoggedInRegisteredUserDomain());
    }

    @Override
    protected RegisteredUser mapUpdatedFieldsToDomain(RegisteredUser entity, RegisteredUserUpdateRequestDTO updatedEntityRequestDTO) {
        entity.setUsername(updatedEntityRequestDTO.getUsername());
        entity.setEmail(updatedEntityRequestDTO.getEmail());
        entity.setFirstName(updatedEntityRequestDTO.getFirstName());
        entity.setLastName(updatedEntityRequestDTO.getLastName());
        return entity;
    }

    @Override
    public AuthenticationResponseDTO updateLoggedInRegisteredUserProfile(RegisteredUserUpdateRequestDTO requestDTO) {
        var id = jwtService.getLoggedInUserId();
        var entity = findByIdDomain(id);
        var updatedEntity = mapUpdatedFieldsToDomain(entity, requestDTO);
        var savedUpdatedEntity = registeredUserRepository.save(updatedEntity);
        var jwt = jwtService.generateJwt(savedUpdatedEntity);
        return new AuthenticationResponseDTO(jwt);
    }

    private RegisteredUser getLoggedInRegisteredUserDomain() {
        return registeredUserRepository.findById(jwtService.getLoggedInUserId()).orElseThrow();
    }

    @Override
    public void updateProfilePicture(MultipartFile file) throws IOException, InterruptedException {
        var loggedInRegisteredUser = getLoggedInRegisteredUserDomain();
        var profilePictureName = loggedInRegisteredUser.getId().toString();
        var profilePicturePath = profilePictureStorage.upload(file, profilePictureName);
        loggedInRegisteredUser.setProfilePicturePath(profilePicturePath);
        registeredUserRepository.save(loggedInRegisteredUser);
    }
    
}
