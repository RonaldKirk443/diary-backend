package com.uio443.diarybackend.primarykeys;

import com.uio443.diarybackend.model.User;

import java.io.Serializable;
import java.util.Objects;

public class CollectionId implements Serializable {

    Long id;
    User user;

    public CollectionId() {}

    public CollectionId(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public boolean equals(CollectionId collectionId) {
        if (this.id.equals(collectionId.id) && this.user.getId().equals(collectionId.user.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
