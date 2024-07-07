package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequestDTO(
    @NotEmpty String username,
    @Email @NotEmpty String email,
    @NotEmpty String password
) { }
