package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.AdvertBehavior;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertBehaviorRepository extends JpaRepository<AdvertBehavior, Long> {
    
    Page<AdvertBehavior> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Optional <AdvertBehavior> findByAdvert(Advert advert);
}