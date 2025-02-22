package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GigListingRequestDTO extends ListingRequestDTO {

    @PositiveOrZero @NotNull
    private Integer bandId;
    
    @NotBlank
    private String title;

    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHour;

    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @PositiveOrZero @NotNull
    private Double maximumAdditionalHours;
    
}
