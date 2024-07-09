package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.repository.BandRepository;
import com.github.veljko121.gigster.service.IBandService;

@Service
public class BandService extends CRUDService<Band, BandRequestDTO, BandResponseDTO, Integer> implements IBandService {

    private final ModelMapper modelMapper;

    // private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository, ModelMapper modelMapper) {
        super(bandRepository);
        // this.bandRepository = bandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected BandResponseDTO mapToResponseDTO(Band entity) {
        return modelMapper.map(entity, BandResponseDTO.class);
    }

    @Override
    protected Band mapToDomain(BandRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Band.class);
    }
    
}
