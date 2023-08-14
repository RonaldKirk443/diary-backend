package com.uio443.diarybackend.primarykeys;

import com.uio443.diarybackend.model.Collection;
import com.uio443.diarybackend.model.User;

import java.io.Serializable;

public class EntryId implements Serializable {

    Long id;
    User user;

    public EntryId() {}

    public EntryId(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public boolean equals(EntryId entryId) {
        return (this.id.equals(entryId.id) && this.user.equals(entryId.user));
    }
}
