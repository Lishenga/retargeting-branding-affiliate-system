package com.retargeting_branding.repositories;

import com.retargeting_branding.models.Advertiser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {
    
    Page<Advertiser> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}