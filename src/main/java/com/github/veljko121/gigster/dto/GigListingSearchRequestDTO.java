package com.github.veljko121.gigster.dto;

import java.util.Collection;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GigListingSearchRequestDTO {
    
    @NotNull @PositiveOrZero
    private Integer page;
    
    @NotNull @PositiveOrZero
    private Integer pageSize;

    private String query;

    private String bandType;

    private Collection<Integer> genreIds;
    
    @PositiveOrZero
    private Double maximumPrice;
    
    @PositiveOrZero
    private Double durationHours;
}
