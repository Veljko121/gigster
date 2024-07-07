package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.core.enums.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
    
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdDateTime;

}
