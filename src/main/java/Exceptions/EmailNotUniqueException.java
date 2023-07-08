package Exceptions;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException() { super("Email already exists"); }

    public EmailNotUniqueException(String message) { super(message); }

}
