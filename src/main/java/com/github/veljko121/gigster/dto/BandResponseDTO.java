package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BandResponseDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    LocalDateTime createdDateTime;
    
}
