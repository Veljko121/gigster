package com.github.veljko121.gigster.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.exception.UnauthorizedOperationException;
import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.GigListingRequestDTO;
import com.github.veljko121.gigster.dto.GigListingResponseDTO;
import com.github.veljko121.gigster.dto.GigListingSearchRequestDTO;
import com.github.veljko121.gigster.dto.GigListingUpdateRequestDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingSearchRequestDTO;
import com.github.veljko121.gigster.model.Genre;
import com.github.veljko121.gigster.model.GigListing;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.repository.BandRepository;
import com.github.veljko121.gigster.repository.GenreRepository;
import com.github.veljko121.gigster.repository.GigListingRepository;
import com.github.veljko121.gigster.repository.RegisteredUserRepository;
import com.github.veljko121.gigster.service.IGigListingService;
import com.github.veljko121.gigster.service.IGigsterSearchEngineService;

import io.jsonwebtoken.lang.Collections;

@Service
public class GigListingService extends CRUDService<GigListing, GigListingRequestDTO, GigListingResponseDTO, GigListingUpdateRequestDTO, Integer> implements IGigListingService {

    private final ModelMapper modelMapper;

    private final GigListingRepository gigListingRepository;
    
    private final RegisteredUserRepository registeredUserRepository;

    private final BandRepository bandRepository;

    private final GenreRepository genreRepository;

    private final IGigsterSearchEngineService gigsterSearchEngineService;
    
    private final IJwtService jwtService;
    
    public GigListingService(GigListingRepository gigListingRepository, RegisteredUserRepository registeredUserRepository, GenreRepository genreRepository, IGigsterSearchEngineService gigsterSearchEngineService, IJwtService jwtService, BandRepository bandRepository, ModelMapper modelMapper) {
        super(gigListingRepository);
        this.gigListingRepository = gigListingRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.registeredUserRepository = registeredUserRepository;
        this.bandRepository = bandRepository;
        this.genreRepository = genreRepository;
        this.gigsterSearchEngineService = gigsterSearchEngineService;
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

    // TODO: Clean-up
    @Override
    public PagedModel<GigListingResponseDTO> searchGigListings(GigListingSearchRequestDTO requestDTO) {
        List<Genre> genres = Collections.emptyList();
        if (requestDTO.getGenreIds() != null) {
            genres = genreRepository.findAllById(requestDTO.getGenreIds());
        }
        var genreNames = genres.stream().map(genre -> genre.getName()).collect(Collectors.toList());
        var gseSearchRequestDTO = modelMapper.map(requestDTO, GSEGigListingSearchRequestDTO.class);
        gseSearchRequestDTO.setGenres(genreNames);
        var result = gigsterSearchEngineService.searchGigListingIdsPaged(gseSearchRequestDTO);
        var ids = result.getContent();
        var pageMetadata = result.getPage();
        var pageable = PageRequest.of(pageMetadata.getNumber(), pageMetadata.getSize());

        List<GigListingResponseDTO> gigListings = new ArrayList<>();
        for (var id : ids) {
            var gigListing = findById(id);
            gigListings.add(gigListing);
        }

        var page = new PageImpl<GigListingResponseDTO>(gigListings, pageable, pageMetadata.getTotalElements());
        var response = new PagedModel<>(page);
        return response;
    }

    @Override
    public Double findMaximumPrice() {
        return gigListingRepository.findMaxMaximumPrice();
    }

    @Override
    public Double findMinimumDurationHours() {
        return gigListingRepository.findMinimumDurationHours();
    }

    @Override
    public Double findMaximumDurationHours() {
        return gigListingRepository.findMaximumDurationHours();
    }
   
}
