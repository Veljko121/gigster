package com.github.veljko121.gigster.core.exception;

public class EmailNotUniqueException extends AttributeNotUniqueException {

    public EmailNotUniqueException(String email) {
        super("email", email);
    }
    
}
