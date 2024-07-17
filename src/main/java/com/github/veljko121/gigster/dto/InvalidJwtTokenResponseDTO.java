package com.github.veljko121.gigster.dto;

import lombok.Data;

@Data
public class InvalidJwtTokenResponseDTO {

    private String message = "Invalid JWT.";
    
}
