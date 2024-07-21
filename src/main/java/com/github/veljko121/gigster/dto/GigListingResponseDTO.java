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
    private Double pricePerAdditionalHour;

    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumAdditionalHours;

    @NotNull
    private LocalDateTime createdDateTime;

}
