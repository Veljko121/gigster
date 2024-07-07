package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    String username;

    @Email
    @NotBlank
    String email;
    
    @NotBlank
    String password;
    
}
