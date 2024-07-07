package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    @NotBlank
    private final String token;

}
