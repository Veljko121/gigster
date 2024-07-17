package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserUpdateRequestDTO;
import com.github.veljko121.gigster.service.IRegisteredUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/registered-users")
@RequiredArgsConstructor
public class RegisteredUserController {

    private final IRegisteredUserService registeredUserService;

    @GetMapping("/{id}")
    public ResponseEntity<RegisteredUserResponseDTO> getById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(registeredUserService.findById(id));
    }
    

    @GetMapping("/profile")
    public ResponseEntity<RegisteredUserResponseDTO> getProfile() {
        return ResponseEntity.ok().body(registeredUserService.getLoggedInRegisteredUser());
    }

    @PutMapping("/update")
    public ResponseEntity<AuthenticationResponseDTO> updateLoggedInUserProfile(@RequestBody RegisteredUserUpdateRequestDTO requestDTO) {
        var responseDTO = registeredUserService.updateLoggedInRegisteredUserProfile(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    
}
