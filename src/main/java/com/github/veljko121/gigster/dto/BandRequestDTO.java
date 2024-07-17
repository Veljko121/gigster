package com.github.veljko121.gigster.dto;

import java.util.Collection;

import com.github.veljko121.gigster.core.enums.BandType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BandRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BandType type;

    @NotEmpty
    private Collection<Integer> genreIds;
    
}
