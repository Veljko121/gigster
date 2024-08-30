package com.github.veljko121.gigster.dto.gigster_search_engine.structs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSEUser extends GSEGenericEntity {

    @NotBlank
    private String username;
    
}
