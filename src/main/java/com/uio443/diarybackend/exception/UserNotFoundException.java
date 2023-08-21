package com.uio443.diarybackend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) { super(String.format("User with ID: %d not found", id)); }

    public UserNotFoundException(String type, String attribute) {
        super(String.format("User with %s: %s not found", type, attribute));
    }

    public UserNotFoundException(String message) { super(message); }

}
