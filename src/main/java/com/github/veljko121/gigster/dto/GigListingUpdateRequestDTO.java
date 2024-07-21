package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GigListingUpdateRequestDTO {

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHour;

    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumAdditionalHours;
    
}
