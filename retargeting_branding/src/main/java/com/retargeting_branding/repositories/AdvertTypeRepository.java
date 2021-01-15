package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.AdvertType;
import com.retargeting_branding.models.AdvertTypeCommissionRate;
import com.retargeting_branding.models.AdvertTypePricing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertTypeRepository extends JpaRepository<AdvertType, Long> {
    
    Page<AdvertType> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Optional <AdvertType> findByAdvertTypeCommissionRate(AdvertTypeCommissionRate advertTypeCommissionRate);

    Optional <AdvertType> findByAdvertTypePricing(AdvertTypePricing advertTypePricing);
}