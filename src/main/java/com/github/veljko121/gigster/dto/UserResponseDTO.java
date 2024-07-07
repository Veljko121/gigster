package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.core.enums.Role;

public record UserResponseDTO(
    String username,
    String email,
    Role role,
    LocalDateTime createdDateTime
) { }
