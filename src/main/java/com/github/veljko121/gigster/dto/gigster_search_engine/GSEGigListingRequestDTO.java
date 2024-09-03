package com.github.veljko121.gigster.dto.gigster_search_engine;

import com.github.veljko121.gigster.dto.gigster_search_engine.structs.GSEBand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSEGigListingRequestDTO extends GSEListingRequestDTO {

    @PositiveOrZero @NotNull
    private GSEBand band;

    @NotBlank
    private String fullTitle;

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHour;

    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumAdditionalHours;
    
}
