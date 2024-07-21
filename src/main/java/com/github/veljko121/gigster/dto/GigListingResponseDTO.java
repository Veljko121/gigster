package com.github.veljko121.gigster.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GigListingResponseDTO {

    @NotNull
    private Integer id;

    @PositiveOrZero @NotNull
    private BandResponseDTO band;

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHalfHour;

    @PositiveOrZero @NotNull
    private Integer minumumDurationHalfHours;

    @PositiveOrZero @NotNull
    private Integer maximumAdditionalHalfHours;

    @NotNull
    private LocalDateTime createdDateTime;

}
