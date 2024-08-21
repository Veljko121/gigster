package com.github.veljko121.gigster.service;

import java.util.Collection;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.GigListingRequestDTO;
import com.github.veljko121.gigster.dto.GigListingResponseDTO;
import com.github.veljko121.gigster.dto.GigListingUpdateRequestDTO;
import com.github.veljko121.gigster.model.GigListing;

public interface IGigListingService extends ICRUDService<GigListing, GigListingRequestDTO, GigListingResponseDTO, GigListingUpdateRequestDTO, Integer> {

    Collection<GigListingResponseDTO> findByBandId(Integer bandId);

    Collection<GigListingResponseDTO> findByLoggedInRegisteredUser();
    
}
