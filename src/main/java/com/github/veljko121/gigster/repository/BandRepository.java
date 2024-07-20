package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.model.RegisteredUser;

import java.util.List;


public interface BandRepository extends JpaRepository<Band, Integer> {

    List<Band> findByOwner(RegisteredUser owner);
    
}
