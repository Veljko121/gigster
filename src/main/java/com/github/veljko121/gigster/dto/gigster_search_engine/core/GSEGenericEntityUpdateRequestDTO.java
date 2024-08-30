package com.github.veljko121.gigster.dto.gigster_search_engine.core;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GSEGenericEntityUpdateRequestDTO {

    @NotNull
    private LocalDateTime createdDateTime;
    
}
