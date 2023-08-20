package com.uio443.diarybackend.service;

import com.uio443.diarybackend.enums.HiddenStatus;
import com.uio443.diarybackend.exception.NotTheFatherException;
import com.uio443.diarybackend.model.Collection;
import com.uio443.diarybackend.model.Entry;
import com.uio443.diarybackend.repository.CollectionRepository;
import com.uio443.diarybackend.repository.EntryRepository;
import com.uio443.diarybackend.repository.UserRepository;
import com.uio443.diarybackend.exception.CollectionNotFoundException;
import com.uio443.diarybackend.exception.EntryNotFoundException;
import com.uio443.diarybackend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class EntryService {

    EntryRepository entryRepository;
    CollectionRepository collectionRepository;
    UserRepository userRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, UserRepository userRepository, CollectionRepository collectionRepository) {
        this.entryRepository = entryRepository;
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    public Entry addEntry(Long userId, Entry entry) {
        entry.setUser(userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        if (entry.getCollectionId() != null) {
            Collection collection = collectionRepository.findCollectionById(entry.getCollectionId()).orElseThrow(() -> new CollectionNotFoundException(entry.getCollectionId()));
            if (!collection.getUser().equals(entry.getUser())) throw new NotTheFatherException(entry.getUser().getId(), collection.getId());
            entry.setCollection(collection);
        }
        if (entry.getTitle() == null) {
            entry.setTitle("Untitled entry");
        }
        entry.setCreatedDate(new Date());
        entry.setEditedDate(new Date());
        if (entry.getText() == null) {
            entry.setText("");
        }
        if (entry.getImages() == null) {
            entry.setImages("");
        }
        if (entry.getHiddenStatus() == HiddenStatus.Default) {
            if (entry.getCollection() != null) {
                entry.setHiddenStatus(entry.getCollection().getHiddenStatus());
            } else {
                entry.setHiddenStatus(entry.getUser().getHiddenStatus());
            }
        }
        return entryRepository.save(entry);
    }

    public Entry updateEntry(Entry entry) {
        Entry oldEntry = entryRepository.findEntryById(entry.getId()).orElseThrow(() -> new EntryNotFoundException(entry.getId()));
        if (entry.getCollectionId() != null) {
            if (entry.getCollectionId() == 0) {
                oldEntry.setCollection(null);
            }
            else {
                Collection collection = collectionRepository.findCollectionById(entry.getCollectionId()).orElseThrow(() -> new CollectionNotFoundException(entry.getCollectionId()));
                if (!collection.getUser().equals(entry.getUser())) throw new NotTheFatherException(entry.getUser().getId(), collection.getId());
                if (!collection.equals(oldEntry.getCollection())) {
                    oldEntry.setCollection(collection);
                }
            }
        }
        if (entry.getTitle() != null && !entry.getTitle().equals(oldEntry.getTitle())) {
            oldEntry.setTitle(entry.getTitle());
        }
        oldEntry.setEditedDate(new Date());
        if (entry.getText() != null && !entry.getText().equals(oldEntry.getText())) {
            oldEntry.setText(entry.getText());
        }
        if (entry.getImages() != null && !entry.getImages().equals(oldEntry.getImages())) {
            oldEntry.setImages(entry.getImages());
        }
        if (entry.getHiddenStatus() != HiddenStatus.Default && entry.getHiddenStatus() != oldEntry.getHiddenStatus()) {
            oldEntry.setHiddenStatus(entry.getHiddenStatus());
        }
        return entryRepository.save(oldEntry);
    }

    public Entry getEntryById(Long id) {
        return entryRepository.findEntryById(id).orElseThrow(() -> new EntryNotFoundException(id));
    }

    public List<Entry> getAllUserEntries(Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return entryRepository.findEntriesByUserId(userId).orElseThrow(() -> new EntryNotFoundException(1, userId));
    }

    public List<Entry> getAllCollectionEntries(Long collectionId) {
        if (!collectionRepository.existsById(collectionId)) throw new CollectionNotFoundException(collectionId);
        return entryRepository.findEntriesByCollectionId(collectionId).orElseThrow(() -> new EntryNotFoundException(2, collectionId));
    }

    public List<Entry> getAllEntriesByTitle(String title) {

        return entryRepository.findEntriesByTitle(title).orElseThrow(() -> new EntryNotFoundException(title));
    }


    public List<Entry> getAllEntriesByTitleAndUserId(Long userId, String title) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return entryRepository.findEntriesByTitleAndUserId(userId, title).orElseThrow(() -> new EntryNotFoundException(title));
    }

    public void deleteEntry(Long entryId) {
        if (!entryRepository.existsByEntryId(entryId)) throw new EntryNotFoundException(entryId);
        entryRepository.deleteEntryById(entryId);
    }

}
