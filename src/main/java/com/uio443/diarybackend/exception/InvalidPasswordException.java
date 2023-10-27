package com.uio443.diarybackend.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() { super("Password does not match"); }

    public InvalidPasswordException(String message) { super(message); }

}
