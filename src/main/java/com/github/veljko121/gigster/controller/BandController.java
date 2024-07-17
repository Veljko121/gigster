package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.dto.BandRequestDTO;
import com.github.veljko121.gigster.dto.BandResponseDTO;
import com.github.veljko121.gigster.service.IBandService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

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
@RequestMapping("/api/bands")
@RequiredArgsConstructor
public class BandController {

    private final IBandService bandService;

    @GetMapping
    public ResponseEntity<Collection<BandResponseDTO>> getAll() {
        return ResponseEntity.ok().body(bandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(bandService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<BandResponseDTO> create(@RequestBody BandRequestDTO requestDTO) {
        var responseDTO = bandService.save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        bandService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BandResponseDTO> update(@PathVariable Integer id, @RequestBody BandRequestDTO updatedEntityRequestDTO) {
        var responseDTO = bandService.update(id, updatedEntityRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    
}
