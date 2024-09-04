package com.github.veljko121.gigster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.veljko121.gigster.model.Band;
import com.github.veljko121.gigster.model.GigListing;
import com.github.veljko121.gigster.model.RegisteredUser;

import java.util.List;


public interface GigListingRepository extends JpaRepository<GigListing, Integer> {
    
    List<GigListing> findByBand(Band band);

    List<GigListing> findByBandOwner(RegisteredUser owner);

    @Query("SELECT MAX(gl.startingPrice + gl.pricePerAdditionalHour * gl.maximumAdditionalHours) FROM GigListing gl")
    Double findMaxMaximumPrice();

    @Query("SELECT MIN(gl.minimumDurationHours) FROM GigListing gl")
    Double findMinimumDurationHours();
    
}
