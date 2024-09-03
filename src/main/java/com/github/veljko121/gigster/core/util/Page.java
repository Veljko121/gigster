package com.github.veljko121.gigster.core.util;

import lombok.Data;

@Data
public class Page {

    private Integer size;

    private Integer number;

    private Integer totalElements;

    private Integer totalPages;
    
}
