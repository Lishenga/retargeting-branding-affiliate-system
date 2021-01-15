package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.PromoCodes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodesRepository extends JpaRepository<PromoCodes, Long> {
    
    Optional <PromoCodes> findByPromoCode(String promoCode);
}