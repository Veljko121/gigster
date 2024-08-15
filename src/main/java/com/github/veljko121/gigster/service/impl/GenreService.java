package com.github.veljko121.gigster.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.service.impl.CRUDService;
import com.github.veljko121.gigster.dto.GenreRequestDTO;
import com.github.veljko121.gigster.dto.GenreResponseDTO;
import com.github.veljko121.gigster.model.Genre;
import com.github.veljko121.gigster.repository.GenreRepository;
import com.github.veljko121.gigster.service.IGenreService;

@Service
public class GenreService extends CRUDService<Genre, GenreRequestDTO, GenreResponseDTO, GenreRequestDTO, Integer> implements IGenreService {
    
    private final ModelMapper modelMapper;

    public GenreService(GenreRepository genreRepository, ModelMapper modelMapper) {
        super(genreRepository);
        this.modelMapper = modelMapper;
    }

    @Override
    protected GenreResponseDTO mapToResponseDTO(Genre entity) {
        var responseDTO = modelMapper.map(entity, GenreResponseDTO.class);
        return responseDTO;
    }

    @Override
    protected Genre mapToDomain(GenreRequestDTO requestDTO) {
        var genre = modelMapper.map(requestDTO, Genre.class);
        return genre;
    }

    @Override
    protected Genre mapUpdatedFieldsToDomain(Genre entity, GenreRequestDTO updatedEntityRequestDTO) {
        entity.setName(updatedEntityRequestDTO.getName());
        return entity;
    }
    
}
