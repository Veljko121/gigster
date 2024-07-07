package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CredentialsDTO {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

}
