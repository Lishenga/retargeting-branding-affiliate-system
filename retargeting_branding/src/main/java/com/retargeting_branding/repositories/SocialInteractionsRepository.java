package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.SocialInteractions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialInteractionsRepository extends JpaRepository<SocialInteractions, Long> {
    
    Optional<SocialInteractions> findByAdvertAndAdvertiser(Advert advert, Advertiser advertiser);
}