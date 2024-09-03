package com.github.veljko121.gigster.service;

import com.github.veljko121.gigster.core.util.PagedResponse;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingRequestDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingResponseDTO;
import com.github.veljko121.gigster.dto.gigster_search_engine.GSEGigListingSearchRequestDTO;
import com.github.veljko121.gigster.model.GigListing;

public interface IGigsterSearchEngineService {

    GSEGigListingResponseDTO createGigListing(GSEGigListingRequestDTO requestDTO);

    GSEGigListingResponseDTO createGigListing(GigListing gigListing);

    void deleteAllGigListings();

    PagedResponse<Integer> searchGigListingIdsPaged(GSEGigListingSearchRequestDTO requestDTO);
    
}
