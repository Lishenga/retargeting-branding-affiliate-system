package com.authorization.authentication.repositories;

import com.authorization.authentication.models.Chat;
import com.authorization.authentication.models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Page<Chat> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    @Query(value = "SELECT u FROM Chat u WHERE u.senderOne = :userOne OR u.senderTwo = :userOne AND u.isDeleted = :isDeleted", nativeQuery = true)
    Page<Chat> findUserChats(@Param("userOne") Users user, @Param("isDeleted") Boolean isDeleted, Pageable pageable);
}