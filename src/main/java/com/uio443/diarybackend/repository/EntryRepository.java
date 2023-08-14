package com.uio443.diarybackend.repository;

import com.uio443.diarybackend.model.Entry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Transactional
    @Query("Select e from Entry e where e.id = ?1")
    Optional<Entry> findEntryById(Long entryId);

    @Transactional
    @Query("Select e from Entry e where e.user.id = ?1")
    Optional<List<Entry>> findEntriesByUserId(Long userId);

    @Transactional
    @Query("Select e from Entry e where e.collection.id = ?1")
    Optional<List<Entry>> findEntriesByCollectionId(Long collectionId);

    @Transactional
    @Query("Select e from Entry e where e.title = ?1")
    Optional<List<Entry>> findEntriesByTitle(String title);

    @Transactional
    @Query("Select e from Entry e where e.user.id = ?1 and e.title = ?2")
    Optional<List<Entry>> findEntriesByTitleAndUserId(Long userId, String title);

    @Modifying
    @Transactional
    @Query("Delete from Entry e where e.id = ?1")
    void deleteEntryById(Long entryId);

    @Transactional
    @Query("SELECT exists(SELECT e from Entry e where e.id = ?1)")
    boolean existsById(long entryId);
}
