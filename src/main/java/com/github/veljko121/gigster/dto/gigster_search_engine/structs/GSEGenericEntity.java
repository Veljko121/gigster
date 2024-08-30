package com.github.veljko121.gigster.dto.gigster_search_engine.structs;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class GSEGenericEntity {
    
    private Integer id;

    private LocalDateTime createdDateTime;

}
