package com.github.veljko121.gigster.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.model.Band;

public interface IBandService extends ICRUDService<Band, BandRequestDTO, BandResponseDTO, BandRequestDTO, Integer> {

    Collection<BandResponseDTO> findByLoggedInUser();

    String updateBandPhoto(MultipartFile file, Integer bandId) throws IOException, InterruptedException;

    byte[] getBandPhoto(Integer bandId) throws IOException, InterruptedException;
    
}
