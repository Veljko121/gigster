package com.github.veljko121.gigster.dto.gigster_search_engine.structs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GSERegisteredUser extends GSEUser {

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
}
