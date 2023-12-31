package com.uio443.diarybackend.repository;

import com.uio443.diarybackend.model.Collection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Transactional
    @Modifying
    @Query("DELETE from Collection c where c.id = ?1")
    void deleteCollectionById(Long id);

    @Transactional
    @Query("SELECT c from Collection c where c.id = ?1")
    Optional<Collection> findCollectionById(Long id);

    @Transactional
    @Query("SELECT c from Collection c where c.title = ?1 and c.hiddenStatus = 2")
    Optional<List<Collection>> findCollectionsByTitle(String title);

    // Potentially should be a list like the findCollectionsByTitle function
    // if we allow user to have multiple collections of same title
    @Transactional
    @Query("SELECT c from Collection c where c.user.id = ?1 and c.title = ?2")
    Optional<List<Collection>> findCollectionByTitleAndUserId(Long userId, String title);

    @Transactional
    @Query("SELECT c from Collection c where c.user.id = ?1")
    Optional<List<Collection>> findCollectionsByUserId(Long userId);

    @Transactional
    @Query("SELECT exists(SELECT c from Collection c where c.id = ?1)")
    boolean existsById(Long id);


}
