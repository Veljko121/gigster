package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.core.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserResponseDTO {
    
    @NotBlank
    String username;

    @NotBlank
    String email;
    
    @NotNull
    Role role;
    
    @NotNull
    LocalDateTime createdDateTime;
    
}
