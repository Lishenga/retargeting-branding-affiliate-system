package com.retargeting_branding.repositories;

import java.util.Optional;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.Invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional <Invoice> findByAdvert(Advert advert);

    Optional <Invoice> findByCompanyAndId(Company comapany, Long id);

    Page <Invoice> findByCompany(Company company, Pageable pageable);
    
    Page<Invoice> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Invoice> findByInvoiceStatus(PaymentStatus invoiceStatus, Pageable pageable);

    Page<Invoice> findByAdvertAndInvoiceStatus(Advert advert, PaymentStatus invoiceStatus, Pageable pageable);

    Page<Invoice> findByCompanyAndInvoiceStatus(Company company, PaymentStatus invoiceStatus, Pageable pageable);

    Page<Invoice> findByPaymentMode(PaymentMode paymentMode, Pageable pageable);

    Page<Invoice> findByAdvertAndPaymentMode(Advert advert, PaymentMode paymentMode, Pageable pageable);

    Page<Invoice> findByCompanyAndPaymentMode(Company company, PaymentMode paymentMode, Pageable pageable);
}