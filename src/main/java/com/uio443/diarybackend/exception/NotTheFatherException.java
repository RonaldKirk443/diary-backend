package com.uio443.diarybackend.exception;

public class NotTheFatherException extends RuntimeException{

    public NotTheFatherException(Long userId, Long collectionId) { super(String.format("User with ID: %d does not own the collection with ID: %d", userId, collectionId)); }

}
