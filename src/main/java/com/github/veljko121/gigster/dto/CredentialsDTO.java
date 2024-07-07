package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotEmpty;

public record CredentialsDTO(
    @NotEmpty String username,
    @NotEmpty String password
) { }
