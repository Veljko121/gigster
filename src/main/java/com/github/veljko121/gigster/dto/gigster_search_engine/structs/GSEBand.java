package com.github.veljko121.gigster.dto.gigster_search_engine.structs;

import java.util.Collection;

import com.github.veljko121.gigster.core.enums.BandType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSEBand extends GSEGenericEntity {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BandType type;

    @NotNull
    private GSERegisteredUser owner;

    private Collection<String> genres;
    
}
