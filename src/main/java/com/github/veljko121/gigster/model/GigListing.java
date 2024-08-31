package com.github.veljko121.gigster.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class GigListing extends Listing {

    @ManyToOne
    @NotNull
    private Band band;

    @NotBlank
    private String title;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHour;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double minimumDurationHours;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double maximumAdditionalHours;

    public Double getMaximumDurationHours() {
        return minimumDurationHours + maximumAdditionalHours;
    }

}
