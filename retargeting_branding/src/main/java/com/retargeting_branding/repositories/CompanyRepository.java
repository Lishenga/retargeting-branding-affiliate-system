package com.retargeting_branding.repositories;

import com.retargeting_branding.models.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    Page<Company> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}