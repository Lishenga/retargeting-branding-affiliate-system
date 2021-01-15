package com.retargeting_branding.repositories;

import java.util.List;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.Status;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.utils.DisplayAdverts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvertRepository extends JpaRepository <Advert, Long> {
    
    Page<Advert> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Advert> findByAdvertStatus(Status advertStatus, Pageable pageable);

    Advert findByRedirectLink(String redirectLink);

    Page<Advert> findByAdvertStatusAndAdvertiser(Status advertStatus, Advertiser advertiser, Pageable pageable);

    Page<Advert> findByAdvertStatusAndCompany(Status advertStatus, Company company, Pageable pageable);

    @Query(value = "CALL getAdverts(:deviceType, :country, :numberOfAds, :identity, :id);", nativeQuery = true)
    List<DisplayAdverts> getDisplayAdverts(@Param("deviceType") String deviceType, @Param("country") String country, @Param("numberOfAds") Integer numberOfAds, @Param("identity") Identity identity, @Param("id") Long id);

}