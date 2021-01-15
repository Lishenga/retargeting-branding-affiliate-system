package com.authorization.authentication.repositories;

import java.util.List;
import java.util.Optional;

import com.authorization.authentication.enums.UsersType;
import com.authorization.authentication.models.Roles;
import com.authorization.authentication.models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional <Users> findByEmail(String email);

    Optional <Users> findByRoles(Roles role);

    List <Users> findListByRoles(Roles role);

    Page<Users> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Users> findByIsDeletedAndRoles(Boolean isDeleted, Roles role, Pageable pageable);

    Page<Users> findByUserTypeAndIsDeleted(UsersType userType, Boolean isDeleted, Pageable pageable);
}