package com.github.veljko121.gigster.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.core.dto.ErrorResponseDTO;
import com.github.veljko121.gigster.core.dto.ExistsResponseDTO;
import com.github.veljko121.gigster.core.exception.EmailNotUniqueException;
import com.github.veljko121.gigster.core.exception.UsernameNotUniqueException;
import com.github.veljko121.gigster.core.service.IJwtService;
import com.github.veljko121.gigster.dto.AuthenticationResponseDTO;
import com.github.veljko121.gigster.dto.CredentialsDTO;
import com.github.veljko121.gigster.dto.RegisterRequestDTO;
import com.github.veljko121.gigster.model.User;
import com.github.veljko121.gigster.service.IAuthenticationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final IJwtService jwtService;

    private final ModelMapper modelMapper;
    private final Logger logger;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        try {
            var user = modelMapper.map(requestDTO, User.class);
            authenticationService.register(user);
    
            var jwt = jwtService.generateJwt(user);
            var authenticationResponse = new AuthenticationResponseDTO(jwt);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

        } catch (UsernameNotUniqueException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
            
        } catch (EmailNotUniqueException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> logIn(@Valid @RequestBody CredentialsDTO credentialsDTO) {
        var user = authenticationService.login(credentialsDTO);
        var jwt = jwtService.generateJwt(user);
        var authenticationResponse = new AuthenticationResponseDTO(jwt);

        return ResponseEntity.ok().body(authenticationResponse);
    }

    @GetMapping("{username}/username-exists")
    public ResponseEntity<ExistsResponseDTO> usernameExists(@PathVariable String username) {
        return ResponseEntity.ok().body(new ExistsResponseDTO(authenticationService.usernameExists(username)));
    }

    @GetMapping("{email}/email-exists")
    public ResponseEntity<ExistsResponseDTO> emailExists(@PathVariable @Email String email) {
        return ResponseEntity.ok().body(new ExistsResponseDTO(authenticationService.emailExists(email)));
    }
    
}
