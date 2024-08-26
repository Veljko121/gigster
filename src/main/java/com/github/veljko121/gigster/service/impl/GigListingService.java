package com.github.veljko121.gigster.service.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.exception.UnauthorizedOperationException;
import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.GigListingRequestDTO;
import com.github.veljko121.gigster.dto.GigListingResponseDTO;
import com.github.veljko121.gigster.dto.GigListingUpdateRequestDTO;
import com.github.veljko121.gigster.model.GigListing;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.repository.BandRepository;
import com.github.veljko121.gigster.repository.GigListingRepository;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.service.IGigListingService;

@Service
public class GigListingService extends CRUDService<GigListing, GigListingRequestDTO, GigListingResponseDTO, GigListingUpdateRequestDTO, Integer> implements IGigListingService {

    private final ModelMapper modelMapper;

    private final GigListingRepository gigListingRepository;
    
    private final RegisteredUserRepository registeredUserRepository;

    private final BandRepository bandRepository;
    
    private final IJwtService jwtService;
    
    public GigListingService(GigListingRepository gigListingRepository, RegisteredUserRepository registeredUserRepository, IJwtService jwtService, BandRepository bandRepository, ModelMapper modelMapper) {
        super(gigListingRepository);
        this.gigListingRepository = gigListingRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.registeredUserRepository = registeredUserRepository;
        this.bandRepository = bandRepository;
    }

    @Override
    public void deleteById(Integer id) {
        checkOwner(id);
        super.deleteById(id);
    }

    @Override
    public GigListingResponseDTO update(Integer id, GigListingUpdateRequestDTO updatedEntityRequestDTO) {
        checkOwner(id);
        return super.update(id, updatedEntityRequestDTO);
    }

    @Override
    protected GigListingResponseDTO mapToResponseDTO(GigListing entity) {
        var responseDTO = modelMapper.map(entity, GigListingResponseDTO.class);
        return responseDTO;
    }

    @Override
    protected GigListing mapToDomain(GigListingRequestDTO requestDTO) {
        var entity = modelMapper.map(requestDTO, GigListing.class);
        entity.setId(null);
        return entity;
    }

    @Override
    protected GigListing mapUpdatedFieldsToDomain(GigListing entity, GigListingUpdateRequestDTO updatedEntityRequestDTO) {
        entity.setMinimumDurationHours(updatedEntityRequestDTO.getMinimumDurationHours());
        entity.setMaximumAdditionalHours(updatedEntityRequestDTO.getMaximumAdditionalHours());
        entity.setStartingPrice(updatedEntityRequestDTO.getStartingPrice());
        entity.setPricePerAdditionalHour(updatedEntityRequestDTO.getPricePerAdditionalHour());
        return entity;
    }

    private void checkOwner(Integer id) {
        var entity = findByIdDomain(id);
        var registeredUser = getLoggedInRegisteredUser();
        var bandOwner = entity.getBand().getOwner();
        if (!bandOwner.equals(registeredUser)) throw new UnauthorizedOperationException();
    }

    private RegisteredUser getLoggedInRegisteredUser() {
        return registeredUserRepository.findById(jwtService.getLoggedInUserId()).orElseThrow();
    }

    @Override
    public Collection<GigListingResponseDTO> findByBandId(Integer bandId) {
        var band = bandRepository.findById(bandId).orElseThrow();
        var entities = gigListingRepository.findByBand(band);
        return mapToResponseDTOs(entities);
    }

    @Override
    public Collection<GigListingResponseDTO> findByLoggedInRegisteredUser() {
        var bands = gigListingRepository.findByBandOwner(getLoggedInRegisteredUser());
        return mapToResponseDTOs(bands);
    }
   
}
