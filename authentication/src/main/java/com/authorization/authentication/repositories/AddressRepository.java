package com.authorization.authentication.repositories;

import java.util.Optional;

import com.authorization.authentication.models.Address;
import com.authorization.authentication.models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    Page<Address> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Optional <Address> findByUser(Users user);

    Optional <Address> findByUserAndIsDeleted(Users user, Boolean isDeleted);
}