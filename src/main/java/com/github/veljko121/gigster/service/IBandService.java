package com.github.veljko121.gigster.service;

import java.util.Collection;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.model.Band;

public interface IBandService extends ICRUDService<Band, BandRequestDTO, BandResponseDTO, BandRequestDTO, Integer> {

    Collection<BandResponseDTO> findByLoggedInUser();
    
}
