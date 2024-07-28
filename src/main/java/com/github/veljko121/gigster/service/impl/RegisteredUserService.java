package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserUpdateRequestDTO;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.service.IRegisteredUserService;
import com.github.veljko121.gigster.sos.IProfilePictureStorage;

@Service
public class RegisteredUserService extends CRUDService<RegisteredUser, RegisterRequestDTO, RegisteredUserResponseDTO, RegisteredUserUpdateRequestDTO, Integer> implements IRegisteredUserService {

    private final ModelMapper modelMapper;

    private final RegisteredUserRepository registeredUserRepository;

    private final IJwtService jwtService;

    private final IProfilePictureStorage profilePictureStorage;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, IJwtService jwtService, IProfilePictureStorage profilePictureStorage, ModelMapper modelMapper) {
        super(registeredUserRepository);
        this.registeredUserRepository = registeredUserRepository;
        this.profilePictureStorage = profilePictureStorage;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @Override
    protected RegisteredUserResponseDTO mapToResponseDTO(RegisteredUser entity) {
        var responseDTO = modelMapper.map(entity, RegisteredUserResponseDTO.class);
        var profilePicturePath = profilePictureStorage.getFullProfilePicturePath(entity);
        responseDTO.setProfilePicturePath(profilePicturePath);
        return responseDTO;
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
    
}
