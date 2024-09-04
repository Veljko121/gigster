package com.github.veljko121.gigster.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    private Collection<String> bandTypes;

    private Collection<Integer> genreIds;
    
    @PositiveOrZero
    private Double maximumPrice;
    
    @PositiveOrZero
    private Double durationHours;

    public Collection<String> getBandTypes() {
        if (bandTypes != null) {
            bandTypes = bandTypes.stream().map(bandType -> bandType.toUpperCase()).toList();
            return bandTypes;
        }
        List<String> emptyList = Collections.emptyList();
        return emptyList;
    }

}
