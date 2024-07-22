package com.github.veljko121.gigster.dto;

import com.github.veljko121.gigster.core.dto.GenericEntityResponseDTO;
import com.github.veljko121.gigster.core.enums.ListingStatus;
import com.github.veljko121.gigster.core.enums.ListingType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ListingResponseDTO extends GenericEntityResponseDTO {
    
    @NotNull
    private ListingType type;
    
    @NotNull
    private ListingStatus status;
    
}
