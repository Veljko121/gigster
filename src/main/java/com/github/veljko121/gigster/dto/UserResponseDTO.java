package com.github.veljko121.gigster.dto;

import com.github.veljko121.gigster.core.dto.GenericEntityResponseDTO;
import com.github.veljko121.gigster.core.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDTO extends GenericEntityResponseDTO {
    
    @NotBlank
    private String username;

    @NotBlank
    private String email;
    
    @NotNull
    private Role role;
    
}
