package com.github.veljko121.gigster.core.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class GenericEntityResponseDTO {

    @NotNull
    private Integer id;

    @NotNull
    private LocalDateTime createdDateTime;
    
}
