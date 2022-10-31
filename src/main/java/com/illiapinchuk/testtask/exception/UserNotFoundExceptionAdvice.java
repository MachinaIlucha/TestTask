package com.illiapinchuk.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Illia Pinchuk
 */
@ControllerAdvice
public class UserNotFoundExceptionAdvice {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
