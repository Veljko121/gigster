package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.Band;

public interface BandRepository extends JpaRepository<Band, Integer> { }
