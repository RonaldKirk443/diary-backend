package Exceptions;

public class TitleNotUniqueException extends RuntimeException{

    public TitleNotUniqueException() { super("Title already exists"); }

    public TitleNotUniqueException(String message) { super(message); }

}
