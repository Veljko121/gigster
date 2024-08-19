package com.github.veljko121.gigster.model;

import java.util.Collection;

import com.github.veljko121.gigster.core.enums.BandType;
import com.github.veljko121.gigster.core.model.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Band extends GenericEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @NotNull
    @Enumerated
    private BandType type;

    @ManyToOne
    @NotNull
    private RegisteredUser owner;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Genre> genres;

    @OneToMany(mappedBy = "band", fetch = FetchType.EAGER)
    private Collection<BandPhoto> photos;
    
}
