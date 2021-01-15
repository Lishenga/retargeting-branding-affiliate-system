package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional <Users> findByEmail(String email);
}