package com.retargeting_branding.repositories;

import com.retargeting_branding.enums.Status;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Website;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
    
    Page<Website> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Website> findByStatus(Status websiteStatus, Pageable pageable);

    Page<Website> findByStatusAndAdvertiser(Status websiteStatus, Advertiser advertiser, Pageable pageable);
}