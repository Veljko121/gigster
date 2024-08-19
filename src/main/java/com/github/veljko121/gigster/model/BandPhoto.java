package com.github.veljko121.gigster.model;

import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BandPhoto extends GenericEntity {

    @NotBlank
    @Column(nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Band band;
    
}
