package com.authorization.authentication.repositories;

import com.authorization.authentication.models.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    
}