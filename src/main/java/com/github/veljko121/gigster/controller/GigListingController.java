package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.dto.GigListingRequestDTO;
import com.github.veljko121.gigster.dto.GigListingResponseDTO;
import com.github.veljko121.gigster.dto.GigListingUpdateRequestDTO;
import com.github.veljko121.gigster.service.IGigListingService;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/gig-listings")
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
    public ResponseEntity<GigListingResponseDTO> create(@RequestBody GigListingRequestDTO requestDTO) {
        return new ResponseEntity<>(gigListingService.save(requestDTO), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        gigListingService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GigListingResponseDTO> update(@PathVariable Integer id, @RequestBody GigListingUpdateRequestDTO updatedEntityRequestDTO) {
        return new ResponseEntity<>(gigListingService.update(id, updatedEntityRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/band/{id}")
    public ResponseEntity<Collection<GigListingResponseDTO>> getByBand(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gigListingService.findByBandId(id));
    }
    
    
}
