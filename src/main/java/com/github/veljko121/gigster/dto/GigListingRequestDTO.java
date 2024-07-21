package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GigListingRequestDTO {

    @PositiveOrZero @NotNull
    private Integer bandId;

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHalfHour;

    @PositiveOrZero @NotNull
    private Integer minumumDurationHalfHours;

    @PositiveOrZero @NotNull
    private Integer maximumAdditionalHalfHours;
    
}
