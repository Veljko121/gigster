package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenreResponseDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;
    
}
