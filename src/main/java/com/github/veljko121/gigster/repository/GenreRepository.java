package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> { }
