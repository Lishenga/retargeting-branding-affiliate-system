package com.retargeting_branding.repositories;

import com.retargeting_branding.models.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    Page<Notification> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}