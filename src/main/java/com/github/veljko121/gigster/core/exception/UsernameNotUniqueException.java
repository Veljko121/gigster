package com.github.veljko121.gigster.core.exception;

public class UsernameNotUniqueException extends AttributeNotUniqueException {

    public UsernameNotUniqueException(String username) {
        super("username", username);
    }
    
}