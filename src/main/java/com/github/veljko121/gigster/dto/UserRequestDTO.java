package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO  {
    
    @NotBlank
    private String username;

    @NotBlank
    private String email;
    
}
