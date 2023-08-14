package exception;

public class UsernameNotUniqueException extends RuntimeException{

    public UsernameNotUniqueException() { super("Username already exists"); }

    public UsernameNotUniqueException(String message) { super(message); }

}
