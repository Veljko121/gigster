package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GigListingResponseDTO extends ListingResponseDTO {

    @PositiveOrZero @NotNull
    private BandResponseDTO band;
    
    @NotBlank
    private String title;
    
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

    @PositiveOrZero @NotNull
    private Double maximumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumPrice;

}
