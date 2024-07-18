package com.github.veljko121.gigster.core.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedOperationException extends RuntimeException {

    public UnauthorizedOperationException(String message) {
        super(message);
    }
    
}