package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BandRequestDTO {

    @NotBlank
    private String name;
    
}