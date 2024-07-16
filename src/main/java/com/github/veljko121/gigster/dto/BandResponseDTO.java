package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import com.github.veljko121.gigster.core.enums.BandType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BandResponseDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private BandType type;

    @NotEmpty
    private Collection<GenreResponseDTO> genres;

    @NotNull
    private RegisteredUserResponseDTO owner;

    @NotNull
    private LocalDateTime createdDateTime;
    
}
