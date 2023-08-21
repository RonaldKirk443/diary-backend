package com.uio443.diarybackend.service;

import com.uio443.diarybackend.exception.CollectionNotFoundException;
import com.uio443.diarybackend.exception.UserNotFoundException;
import com.uio443.diarybackend.enums.HiddenStatus;
import com.uio443.diarybackend.model.Collection;
import com.uio443.diarybackend.repository.CollectionRepository;
import com.uio443.diarybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    CollectionRepository collectionRepository;
    UserRepository userRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository, UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    public Collection addCollection(Long userId, Collection collection) {
        collection.setUser(userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        if (collection.getTitle() == null) {
            collection.setTitle("Untitled Collection");
        }
        if (collection.getDescription() == null) {
            collection.setDescription("");
        }
        if (collection.getHiddenStatus() == HiddenStatus.Default) {
            collection.setHiddenStatus(HiddenStatus.Private);
        }
        collection.setBackgroundImgLink("https://a.ppy.sh/12025261?1673568592.jpeg");
        return collectionRepository.save(collection);
    }

    public void deleteCollection(Long id) {
        if (!collectionRepository.existsById(id)) throw new CollectionNotFoundException(id);

        collectionRepository.deleteCollectionById(id);
    }

    public List<Collection> getAllUserCollections(Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);

        return collectionRepository.findCollectionsByUserId(userId).orElseThrow(() -> new CollectionNotFoundException(1, userId));
    }

    public Collection getCollectionById(Long id) {
        return collectionRepository.findCollectionById(id).orElseThrow(() -> new CollectionNotFoundException(id));
    }


    public List<Collection> getCollectionByTitleAndUserId(String title, Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);

        return collectionRepository.findCollectionByTitleAndUserId(userId, title).orElseThrow(() -> new CollectionNotFoundException(title, userId));
    }


    public List<Collection> getAllCollectionsByTitle(String title) {
        return collectionRepository.findCollectionsByTitle(title).orElseThrow(() -> new CollectionNotFoundException(title));
    }

    public Collection updateCollection(Collection collection) {
        Long id = collection.getId();
        Collection oldCollection = collectionRepository.findCollectionById(id).orElseThrow(() -> new CollectionNotFoundException(id));

        String newTitle = collection.getTitle();
        String newBackgroundImgLink = collection.getBackgroundImgLink();
        HiddenStatus newHiddenStatus = collection.getHiddenStatus();
        String newDescription = collection.getDescription();

        if (newTitle != null && !newTitle.equals("") && !newTitle.equals(oldCollection.getTitle())) {
            oldCollection.setTitle(newTitle);
        }

        if (newBackgroundImgLink != null && !newBackgroundImgLink.equals(oldCollection.getBackgroundImgLink())) {
            oldCollection.setBackgroundImgLink(newBackgroundImgLink);
        }

        if (newHiddenStatus != oldCollection.getHiddenStatus() && newHiddenStatus != HiddenStatus.Default) {
            oldCollection.setHiddenStatus(newHiddenStatus);
        }

        if (newDescription != null && !newDescription.equals(oldCollection.getDescription())) {
            oldCollection.setDescription(newDescription);
        }

        return collectionRepository.save(oldCollection);

    }

}
