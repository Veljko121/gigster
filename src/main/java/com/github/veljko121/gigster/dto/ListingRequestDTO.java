package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ListingRequestDTO {

    @PositiveOrZero
    private Integer durationDays;
    
}
