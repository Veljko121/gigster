package com.github.veljko121.gigster.dto.gigster_search_engine;

import java.util.Collection;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GSEGigListingSearchRequestDTO {

    @NotNull @PositiveOrZero
    private Integer page;
    
    @NotNull @PositiveOrZero
    private Integer pageSize;

    private String query;

    private String bandType;

    private Collection<String> genres;
    
    @PositiveOrZero
    private Double maximumPrice;
    
    @PositiveOrZero
    private Double durationHours;

}
