package com.authorization.authentication.repositories;

import com.authorization.authentication.models.Permissions;
import com.authorization.authentication.models.Roles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    
    Page<Permissions> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Permissions> findByRoles(Roles role, Boolean isDeleted, Pageable pageable);
}