package com.authorization.authentication.repositories;

import java.util.Optional;

import com.authorization.authentication.models.Clients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    
    Optional<Clients> findByClientIdAndClientSecret(String clientId, String clientSecret);

    Clients findByClientSecret(String clientSecret);
}