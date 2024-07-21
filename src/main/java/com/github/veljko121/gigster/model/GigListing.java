package com.github.veljko121.gigster.model;

import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class GigListing extends GenericEntity {

    @ManyToOne
    @NotNull
    private Band band;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double startingPrice;
    
    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Double pricePerAdditionalHalfHour;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Integer minumumDurationHalfHours;

    @Column(nullable = false)
    @PositiveOrZero @NotNull
    private Integer maximumAdditionalHalfHours;

}
