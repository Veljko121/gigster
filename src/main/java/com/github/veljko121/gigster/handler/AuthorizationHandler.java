package com.github.veljko121.gigster.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.veljko121.gigster.core.exception.UnauthorizedOperationException;
import com.github.veljko121.gigster.dto.InvalidJwtTokenResponseDTO;

import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class AuthorizationHandler {

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<?> handeException(UnauthorizedOperationException exception) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handeJwtException(MalformedJwtException exception) {
        return new ResponseEntity<>(new InvalidJwtTokenResponseDTO(), HttpStatus.UNAUTHORIZED);
    }
    
}
