package com.github.veljko121.gigster.dto.gigster_search_engine;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.dto.gigster_search_engine.core.GSEGenericEntityResponseDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSEListingResponseDTO extends GSEGenericEntityResponseDTO {

    @PositiveOrZero
    private Integer durationDays;

    @NotNull
    private LocalDateTime endDate;

    private boolean active;
    
}
