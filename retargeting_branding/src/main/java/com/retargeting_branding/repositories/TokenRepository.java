package com.retargeting_branding.repositories;

import com.retargeting_branding.models.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    
}