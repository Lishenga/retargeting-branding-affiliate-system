package com.authorization.authentication.repositories;

import java.util.List;

import com.authorization.authentication.models.Roles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    
    Page<Roles> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    
    @Query(value = "SELECT * FROM Roles INNER JOIN Users ON role.id = Users.roles WHERE Users.id = :userId", nativeQuery = true)
    List<Roles> findUserRoles(@Param("userId") Long userId);
}