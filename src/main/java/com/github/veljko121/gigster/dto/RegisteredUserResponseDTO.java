package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisteredUserResponseDTO extends UserResponseDTO {

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    private String profilePicturePath;

}
