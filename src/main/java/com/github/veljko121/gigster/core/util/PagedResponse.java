package com.github.veljko121.gigster.core.util;

import java.util.Collection;

import lombok.Data;

@Data
public class PagedResponse<T> {

    private Collection<T> content;

    private Page page;
    
}
