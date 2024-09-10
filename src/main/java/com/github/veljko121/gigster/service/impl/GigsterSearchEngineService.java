package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.veljko121.gigster.core.util.PagedResponse;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingRequestDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingResponseDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingSearchRequestDTO;
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

    private GSEGigListingRequestDTO mapToUpdateRequestDTO(GigListing gigListing) {
        return mapToRequestDTO(gigListing);
    }

    @Override
    public void updateGigListing(Integer id, GigListing gigListing) {
        restTemplate.put(gigListingsUrl() + "/" + id, mapToUpdateRequestDTO(gigListing), GSEGigListingResponseDTO.class);
    }

    @Override
    public void deleteGigListingById(Integer id) {
        restTemplate.delete(gigListingsUrl() + "/" + id);
    }

    @Override
    public void deleteAllGigListings() {
        restTemplate.delete(gigListingsUrl());
    }

    @Override
    public PagedResponse<Integer> searchGigListingIdsPaged(GSEGigListingSearchRequestDTO requestDTO) {
        var url = UriComponentsBuilder.fromHttpUrl(gigListingsUrl()).path("/search/ids")
            .queryParam("page", requestDTO.getPage())
            .queryParam("pageSize", requestDTO.getPageSize())
            .queryParam("query", requestDTO.getQuery())
            .queryParam("bandTypes", requestDTO.getBandTypes())
            .queryParam("genres", requestDTO.getGenres())
            .queryParam("maximumPrice", requestDTO.getMaximumPrice())
            .queryParam("durationHours", requestDTO.getDurationHours())
            .queryParam("sortBy", requestDTO.getSortBy()).build().toString();
            // .toUriString();

        var response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<PagedResponse<Integer>>() {}
        );

        return response.getBody();
    }
    
}
