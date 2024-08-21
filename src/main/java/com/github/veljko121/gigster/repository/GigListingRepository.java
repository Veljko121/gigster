package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.model.GigListing;
import com.github.veljko121.gigster.model.RegisteredUser;

import java.util.List;


public interface GigListingRepository extends JpaRepository<GigListing, Integer> {
    
    List<GigListing> findByBand(Band band);

    List<GigListing> findByBandOwner(RegisteredUser owner);
    
}
