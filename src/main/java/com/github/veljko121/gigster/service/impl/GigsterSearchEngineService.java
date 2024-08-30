package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingRequestDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingResponseDTO;
import com.github.veljko121.gigster.model.GigListing;
import com.github.veljko121.gigster.service.IGigsterSearchEngineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GigsterSearchEngineService implements IGigsterSearchEngineService {

    @Value("${gigster-search-engine.host}")
    private String gigsterSearchEngineHost;

    @Value("${gigster-search-engine.port}")
    private String gigsterSearchEnginePort;

    private final ModelMapper modelMapper;

    private String url() {
        return "http://" + gigsterSearchEngineHost + ':' + gigsterSearchEnginePort;
    }

    private String apiUrl() {
        return url() + "/api";
    }

    private String listingsUrl() {
        return apiUrl() + "/listings";
    }

    private String gigListingsUrl() {
        return listingsUrl() + "/gigs";
    }

    private final RestTemplate restTemplate;
    
    @Override
    public GSEGigListingResponseDTO createGigListing(GSEGigListingRequestDTO requestDTO) {
        return restTemplate.postForObject(gigListingsUrl(), requestDTO, GSEGigListingResponseDTO.class);
    }

    @Override
    public GSEGigListingResponseDTO createGigListing(GigListing gigListing) {
        return createGigListing(mapToRequestDTO(gigListing));
    }

    private GSEGigListingRequestDTO mapToRequestDTO(GigListing gigListing) {
        var requestDTO = modelMapper.map(gigListing, GSEGigListingRequestDTO.class);
        requestDTO.getBand().getGenres().clear();
        for (var genre : gigListing.getBand().getGenres()) {
            requestDTO.getBand().getGenres().add(genre.getName());
        }
        return requestDTO;
    }

    @Override
    public void deleteAllGigListings() {
        restTemplate.delete(gigListingsUrl());
    }
    
}
