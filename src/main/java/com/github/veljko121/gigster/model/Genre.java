package com.github.veljko121.gigster.model;

import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Genre extends GenericEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    
}
