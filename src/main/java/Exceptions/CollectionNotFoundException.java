package Exceptions;

public class CollectionNotFoundException extends RuntimeException{

    public CollectionNotFoundException(Long id) { super(String.format("Collection with ID: %d does not exist", id)); }

    public CollectionNotFoundException(int type, Long userId) { super(String.format("User with ID: %d does not have any collections", userId)); }

    public CollectionNotFoundException(String title) { super(String.format("Collection with title: %s does not exist", title)); }

    public CollectionNotFoundException(String title, Long id) { super(String.format("User with ID: %d does not have collection with title: %s", id, title)); }

}
