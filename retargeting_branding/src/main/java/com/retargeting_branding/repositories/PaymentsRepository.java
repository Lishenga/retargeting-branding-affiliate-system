package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.Invoice;
import com.retargeting_branding.models.Payments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    
    Page<Payments> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Payments> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

    Page<Payments> findByPaymentMode(PaymentMode paymentMode, Pageable pageable);

    Page<Payments> findByAdvert(Advert advert, Pageable pageable);

    Page<Payments> findByCompany(Company company, Pageable pageable);

    Optional<Payments> findByInvoice(Invoice invoice);
}