package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserResponseDTO;
import com.github.veljko121.gigster.dto.RegisteredUserUpdateRequestDTO;
import com.github.veljko121.gigster.service.IRegisteredUserService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


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
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<RegisteredUserResponseDTO> getProfile() {
        return ResponseEntity.ok().body(registeredUserService.getLoggedInRegisteredUser());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<AuthenticationResponseDTO> updateLoggedInUserProfile(@RequestBody RegisteredUserUpdateRequestDTO requestDTO) {
        var responseDTO = registeredUserService.updateLoggedInRegisteredUserProfile(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PatchMapping("/profile-picture")
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<?> updateProfilePicture(@RequestPart("file") MultipartFile file) throws IOException, InterruptedException {
        registeredUserService.updateProfilePicture(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile-picture")
    public ResponseEntity<byte[]> getProfilePictureForLoggedInRegisteredUser() throws IOException, InterruptedException {
        var profilePicture = registeredUserService.getProfilePictureForLoggedInRegisteredUser();
        return ResponseEntity.ok(profilePicture);
    }

    @GetMapping("/{id}/profile-picture")
    public ResponseEntity<byte[]> getProfilePictureByRegisteredUserId(@PathVariable Integer id) throws IOException, InterruptedException {
        var profilePicture = registeredUserService.getProfilePictureByRegisteredUserId(id);
        return ResponseEntity.ok(profilePicture);
    }
    
}
