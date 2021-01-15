package com.retargeting_branding.repositories;

import com.retargeting_branding.models.AdEvents;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Website;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdEventsRepository extends JpaRepository<AdEvents, Long> {
    
    Page<AdEvents> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<AdEvents> findByAdvertiser(Advertiser advertiser, Pageable pageable);

    Page<AdEvents> findByWebsite(Website website, Pageable pageable);
}