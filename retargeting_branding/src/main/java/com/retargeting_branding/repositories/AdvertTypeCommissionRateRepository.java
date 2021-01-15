package com.retargeting_branding.repositories;

import com.retargeting_branding.models.AdvertTypeCommissionRate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertTypeCommissionRateRepository extends JpaRepository<AdvertTypeCommissionRate, Long> {
    
    Page<AdvertTypeCommissionRate> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}