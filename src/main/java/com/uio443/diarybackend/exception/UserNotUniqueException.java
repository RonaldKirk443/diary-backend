package com.uio443.diarybackend.exception;

public class UserNotUniqueException extends RuntimeException{

    public UserNotUniqueException() { super("User already exists"); }

    public UserNotUniqueException(String message) { super(message); }

}
