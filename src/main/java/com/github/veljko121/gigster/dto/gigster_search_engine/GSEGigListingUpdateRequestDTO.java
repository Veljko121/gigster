package com.github.veljko121.gigster.dto.gigster_search_engine;

import com.github.veljko121.gigster.dto.gigster_search_engine.structs.GSEBand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSEGigListingUpdateRequestDTO extends GSEListingUpdateRequestDTO {

    @PositiveOrZero @NotNull
    private GSEBand band;

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHour;

    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumDurationHours;
    
}
