package com.authorization.authentication.repositories;

import java.util.Optional;

import com.authorization.authentication.models.Chat;
import com.authorization.authentication.models.Messages;
import com.authorization.authentication.models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    Page<Messages> findByChat(Chat chat, Pageable pageable);

    @Query(value = "SELECT u FROM Messages u WHERE u.sender = :userOne OR u.receiver = :userTwo AND u.isDeleted = :isDeleted", nativeQuery = true)
    Page<Messages> findAllUserMessagesWithPagination(@Param("userOne") Users sender, @Param("userTwo") Users receiver, @Param("isDeleted") Boolean isDeleted, Pageable pageable);

    Optional <Messages> findBySender(Users sender);

    Optional <Messages> findByReceiver(Users receiver);

    Page<Messages> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    
}