package com.github.veljko121.gigster.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CredentialsDTO {

    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
}
