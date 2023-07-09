package com.uio443.diarybackend.primarykeys;

import com.uio443.diarybackend.model.User;

import java.io.Serializable;
import java.util.Objects;

public class CollectionUserId implements Serializable {

    Long id;
    User user;

    public CollectionUserId() {}

    public CollectionUserId(Long id, User user) {
        this.id = id;
        this.user = user;
    }


    public boolean equals(CollectionUserId collectionUserId) {
        if (this.id.equals(collectionUserId.id) && this.user.getId().equals(collectionUserId.user.getId())) {
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
