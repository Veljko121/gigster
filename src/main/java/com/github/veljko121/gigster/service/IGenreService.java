package com.github.veljko121.gigster.service;

import com.github.veljko121.gigster.core.service.ICRUDService;
import com.github.veljko121.gigster.dto.GenreRequestDTO;
import com.github.veljko121.gigster.dto.GenreResponseDTO;
import com.github.veljko121.gigster.model.Genre;

public interface IGenreService extends ICRUDService<Genre, GenreRequestDTO, GenreResponseDTO, GenreRequestDTO, Integer> { }
