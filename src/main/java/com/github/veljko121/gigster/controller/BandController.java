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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public ResponseEntity<BandResponseDTO> create(@RequestBody BandRequestDTO requestDTO) {
        var responseDTO = bandService.save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    
}