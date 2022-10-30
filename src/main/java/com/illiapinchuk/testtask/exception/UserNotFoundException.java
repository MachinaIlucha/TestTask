package com.illiapinchuk.testtask.exception;

/**
 * @author Illia Pinchuk
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("Could not find user with id " + userId);
    }
}
