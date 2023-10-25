package com.uio443.diarybackend.repository;

import com.uio443.diarybackend.model.Login;
import com.uio443.diarybackend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Transactional
    @Modifying
    @Query("DELETE from Login l where l.userId = ?1")
    void deleteByUserId(Long userId);

    @Transactional
    @Query("SELECT l from Login l where l.userId = ?1")
    Optional<Login> findByUserId(Long id);

    @Transactional
    @Query("SELECT l from Login l where l.email = ?1")
    Optional<Login> findByEmail(String email);

    @Transactional
    @Query("SELECT exists(SELECT u from User u where u.id = ?1)")
    boolean existsById(Long id);

    @Transactional
    @Query("SELECT exists(SELECT l from Login l where l.id = ?1)")
    boolean existsByUserId(Long id);

    @Transactional
    @Query("SELECT exists(SELECT l from Login l where l.email = ?1)")
    boolean existsByEmail(String email);

}
