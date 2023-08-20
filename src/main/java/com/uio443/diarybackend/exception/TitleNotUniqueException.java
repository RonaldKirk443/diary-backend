package com.uio443.diarybackend.exception;

public class TitleNotUniqueException extends RuntimeException{

    public TitleNotUniqueException() { super("Title already exists"); }

    public TitleNotUniqueException(String message) { super(message); }

}
