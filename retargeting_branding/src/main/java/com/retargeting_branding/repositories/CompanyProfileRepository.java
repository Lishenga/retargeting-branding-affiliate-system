package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.CompanyProfile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long>  {
    
    Optional<CompanyProfile> findByCompany(Company company);

    Page<CompanyProfile> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}