package com.github.veljko121.gigster.dto;

import com.github.veljko121.gigster.core.dto.GenericEntityResponseDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BandPhotoResponseDTO extends GenericEntityResponseDTO {
    
    @NotBlank
    private String path;

}
