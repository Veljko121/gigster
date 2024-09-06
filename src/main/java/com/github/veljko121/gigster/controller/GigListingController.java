package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.dto.GigListingRequestDTO;
import com.github.veljko121.gigster.dto.GigListingResponseDTO;
import com.github.veljko121.gigster.dto.GigListingSearchRequestDTO;
import com.github.veljko121.gigster.dto.GigListingUpdateRequestDTO;
import com.github.veljko121.gigster.service.IGigListingService;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/listings/gigs")
@RequiredArgsConstructor
public class GigListingController {

    private final IGigListingService gigListingService;

    @GetMapping
    public ResponseEntity<Collection<GigListingResponseDTO>> getAll() {
        return ResponseEntity.ok().body(gigListingService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GigListingResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gigListingService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<GigListingResponseDTO> create(@RequestBody GigListingRequestDTO requestDTO) {
        return new ResponseEntity<>(gigListingService.save(requestDTO), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        gigListingService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<GigListingResponseDTO> update(@PathVariable Integer id, @RequestBody GigListingUpdateRequestDTO updatedEntityRequestDTO) {
        return new ResponseEntity<>(gigListingService.update(id, updatedEntityRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/band/{id}")
    public ResponseEntity<Collection<GigListingResponseDTO>> getByBand(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gigListingService.findByBandId(id));
    }
    
    @GetMapping("/my")
    public ResponseEntity<Collection<GigListingResponseDTO>> getByLoggedInRegisteredUser() {
        return ResponseEntity.ok().body(gigListingService.findByLoggedInRegisteredUser());
    }

    @GetMapping("/search")
    public ResponseEntity<PagedModel<GigListingResponseDTO>> search(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Collection<String> bandTypes,
            @RequestParam(required = false) Collection<Integer> genreIds,
            @RequestParam(required = false) Double maximumPrice,
            @RequestParam(required = false) Double durationHours,
            @RequestParam(required = false) String sortBy
    ) {
        var requestDTO = new GigListingSearchRequestDTO(page, pageSize, query, bandTypes, genreIds, maximumPrice, durationHours, sortBy);
        return ResponseEntity.ok(gigListingService.searchGigListings(requestDTO));
    }

    @GetMapping("/maximum-price")
    public ResponseEntity<Double> getMaximumPrice() {
        return ResponseEntity.ok(gigListingService.findMaximumPrice());
    }

    @GetMapping("/minimum-hours")
    public ResponseEntity<Double> getMinimumHours() {
        return ResponseEntity.ok(gigListingService.findMinimumDurationHours());
    }

    @GetMapping("/maximum-hours")
    public ResponseEntity<Double> getMaximumHours() {
        return ResponseEntity.ok(gigListingService.findMaximumDurationHours());
    }
    
}
