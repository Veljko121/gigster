package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.core.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisteredUserResponseDTO {

    @NotNull
    private Integer id;
    
    @NotBlank
    private String username;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private String email;
    
    @NotNull
    private Role role;
    
    @NotNull
    private LocalDateTime createdDateTime;

}
