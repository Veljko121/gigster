package com.github.veljko121.gigster.service.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.exception.UnauthorizedOperationException;
import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.repository.BandRepository;
import com.github.veljko121.gigster.repository.GenreRepository;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.service.IBandService;

@Service
public class BandService extends CRUDService<Band, BandRequestDTO, BandResponseDTO, BandRequestDTO, Integer> implements IBandService {

    private final ModelMapper modelMapper;

    private final BandRepository bandRepository;

    private final GenreRepository genreRepository;

    private final RegisteredUserRepository registeredUserRepository;

    private final IJwtService jwtService;

    public BandService(BandRepository bandRepository, GenreRepository genreRepository, RegisteredUserRepository registeredUserRepository, IJwtService jwtService, ModelMapper modelMapper) {
        super(bandRepository);
        this.bandRepository = bandRepository;
        this.genreRepository = genreRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(Integer id) {
        checkOwner(id);
        super.deleteById(id);
    }
    
    @Override
    public BandResponseDTO update(Integer id, BandRequestDTO updatedEntityRequestDTO) {
        checkOwner(id);
        return super.update(id, updatedEntityRequestDTO);
    }

    @Override
    protected BandResponseDTO mapToResponseDTO(Band entity) {
        var responseDTO = modelMapper.map(entity, BandResponseDTO.class);
        return responseDTO;
    }

    @Override
    protected Band mapToDomain(BandRequestDTO requestDTO) {
        var band = modelMapper.map(requestDTO, Band.class);
        band.setGenres(genreRepository.findAllById(requestDTO.getGenreIds()));
        band.setOwner(getLoggedInRegisteredUser());
        return band;
    }

    @Override
    protected Band mapUpdatedFieldsToDomain(Band entity, BandRequestDTO updatedEntityRequestDTO) {
        entity.setName(updatedEntityRequestDTO.getName());
        entity.setDescription(updatedEntityRequestDTO.getDescription());
        entity.setGenres(genreRepository.findAllById(updatedEntityRequestDTO.getGenreIds()));
        entity.setType(updatedEntityRequestDTO.getType());
        return entity;
    }

    private void checkOwner(Integer id) {
        var band = findByIdDomain(id);
        var registeredUser = getLoggedInRegisteredUser();
        if (band.getOwner() != registeredUser) throw new UnauthorizedOperationException();
    }

    private RegisteredUser getLoggedInRegisteredUser() {
        return registeredUserRepository.findById(jwtService.getLoggedInUserId()).orElseThrow();
    }

    @Override
    public Collection<BandResponseDTO> findByLoggedInUser() {
        var bands = bandRepository.findByOwner(getLoggedInRegisteredUser());
        return mapToResponseDTOs(bands);
    }
    
}
