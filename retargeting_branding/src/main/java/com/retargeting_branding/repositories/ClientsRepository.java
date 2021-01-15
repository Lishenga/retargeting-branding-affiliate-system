package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.Clients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    
    Optional<Clients> findByClientIdAndClientSecret(String clientId, String clientSecret);

    Clients findByClientSecret(String clientSecret);
}