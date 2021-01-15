package com.retargeting_branding.repositories;

import com.retargeting_branding.enums.PricingMode;
import com.retargeting_branding.models.AdvertTypePricing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertTypePricingRepository extends JpaRepository<AdvertTypePricing, Long> {
    
    Page<AdvertTypePricing> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<AdvertTypePricing> findByPricingMode(PricingMode pricingMode, Pageable pageable);
}