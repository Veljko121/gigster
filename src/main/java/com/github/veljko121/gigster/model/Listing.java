package com.github.veljko121.gigster.model;

import java.time.LocalDateTime;

import com.github.veljko121.gigster.core.enums.ListingType;
import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Listing extends GenericEntity {

    @Enumerated
    @Column(insertable = false, columnDefinition = "smallint NOT NULL DEFAULT 0")
    @NotNull
    private ListingType type = ListingType.GIG;

    @NotNull @Positive
    @Column(nullable = false)
    private Integer durationDays;

    public LocalDateTime getEndDate() {
        return getCreatedDateTime().plusDays(durationDays);
    }

    public boolean isActive() {
        var now = LocalDateTime.now();
        return now.isAfter(getCreatedDateTime()) && now.isBefore(getEndDate());
    }
    
}
