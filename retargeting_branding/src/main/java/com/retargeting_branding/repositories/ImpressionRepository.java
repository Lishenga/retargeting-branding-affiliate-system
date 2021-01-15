package com.retargeting_branding.repositories;

import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Impression;
import com.retargeting_branding.models.Website;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpressionRepository extends JpaRepository<Impression, Long> {
    
    Page<Impression> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Impression> findByAdvertiser(Advertiser advertiser, Pageable pageable);

    Page<Impression> findByWebsite(Website website, Pageable pageable);
}