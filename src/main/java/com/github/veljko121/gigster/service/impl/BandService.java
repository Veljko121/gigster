package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.repository.BandRepository;
import com.github.veljko121.gigster.repository.GenreRepository;
import com.github.veljko121.gigster.service.IBandService;
import com.github.veljko121.gigster.service.IRegisteredUserService;

@Service
public class BandService extends CRUDService<Band, BandRequestDTO, BandResponseDTO, Integer> implements IBandService {

    private final ModelMapper modelMapper;

    // private final BandRepository bandRepository;

    private final GenreRepository genreRepository;

    private final IRegisteredUserService registeredUserService;

    public BandService(BandRepository bandRepository, GenreRepository genreRepository, IRegisteredUserService registeredUserService, ModelMapper modelMapper) {
        super(bandRepository);
        // this.bandRepository = bandRepository;
        this.genreRepository = genreRepository;
        this.registeredUserService = registeredUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected BandResponseDTO mapToResponseDTO(Band entity) {
        var responseDTO = modelMapper.map(entity, BandResponseDTO.class);
        return responseDTO;
    }

    @Override
    protected Band mapToDomain(BandRequestDTO requestDTO) {
        var band = modelMapper.map(requestDTO, Band.class);
        var genres = this.genreRepository.findAllById(requestDTO.getGenreIds());
        band.setGenres(genres);
        band.setOwner(registeredUserService.getLoggedInRegisteredUser());
        return band;
    }
    
}
