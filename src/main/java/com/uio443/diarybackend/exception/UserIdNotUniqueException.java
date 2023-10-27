package com.uio443.diarybackend.exception;

public class UserIdNotUniqueException extends RuntimeException{

    public UserIdNotUniqueException() { super("UserId already exists"); }

    public UserIdNotUniqueException(Long id) { super("UserId with id " + id +  "already exists"); }

    public UserIdNotUniqueException(String message) { super(message); }

}
