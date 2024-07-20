package com.github.veljko121.gigster.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.ChangePasswordRequestDTO;
import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.service.IAuthenticationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        var jwt = authenticationService.register(requestDTO);
        var authenticationResponse = new AuthenticationResponseDTO(jwt);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody CredentialsDTO credentialsDTO) {
        var jwt = authenticationService.login(credentialsDTO);
        var authenticationResponse = new AuthenticationResponseDTO(jwt);
        return ResponseEntity.ok().body(authenticationResponse);
    }

    @GetMapping("{username}/username-exists")
    public ResponseEntity<?> usernameExists(@PathVariable String username) {
        return ResponseEntity.ok().body(authenticationService.usernameExists(username));
    }

    @GetMapping("{email}/email-exists")
    public ResponseEntity<?> emailExists(@PathVariable @Email String email) {
        return ResponseEntity.ok().body(authenticationService.emailExists(email));
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
        authenticationService.changePassword(requestDTO);
        return ResponseEntity.ok().build();
    }
    
}
