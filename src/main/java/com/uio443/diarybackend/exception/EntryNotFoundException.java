package com.uio443.diarybackend.exception;

public class EntryNotFoundException extends RuntimeException{

    public EntryNotFoundException(Long id) { super(String.format("Entry with ID: %d does not exist", id)); }

    public EntryNotFoundException(int type, Long userId) {
        // 1 User
        // 2 Collection
        super(String.format("%s with ID: %d does not have any entries", type == 1 ? "User" : "Collection" , userId));
    }

    public EntryNotFoundException(String title) { super(String.format("Entry with title: %s does not exist", title)); }

    public EntryNotFoundException(String title, Long id) { super(String.format("User with ID: %d does not have an entry with title: %s", id, title)); }

}
