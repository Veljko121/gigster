package com.github.veljko121.gigster.model;

import com.github.veljko121.gigster.core.enums.ListingStatus;
import com.github.veljko121.gigster.core.enums.ListingType;
import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Listing extends GenericEntity {

    @Enumerated
    @Column(insertable = false, columnDefinition = "smallint NOT NULL DEFAULT 0")
    @NotNull
    private ListingType type;
    
    @Enumerated
    @NotNull
    @Column(insertable = false, columnDefinition = "smallint NOT NULL DEFAULT 0")
    private ListingStatus status;
    
}
