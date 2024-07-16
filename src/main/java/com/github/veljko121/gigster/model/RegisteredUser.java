package com.github.veljko121.gigster.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RegisteredUser extends User {

    @NotBlank
    @Column(nullable = false, length = 30)
    private String firstName;
    
    @NotBlank
    @Column(nullable = false, length = 30)
    private String lastName;
    
}
