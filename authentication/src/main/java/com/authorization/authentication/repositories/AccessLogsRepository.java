package com.authorization.authentication.repositories;

import com.authorization.authentication.models.AccessLogs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogsRepository extends JpaRepository<AccessLogs, Long> {
    
    Page<AccessLogs> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}