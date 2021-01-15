package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.requests.payments.CreatePaymentRequest;
import com.retargeting_branding.requests.payments.UpdatePaymentRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@Getter @Setter @ToString
public class Payments {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Invoice invoice;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advert advert;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus paymentStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentMode paymentMode;

    @Column(name = "payment_reference", nullable = false)
    private String paymentReference;

    @Column(name = "payer_identity", nullable = false)
    private String payerIdentity;

    @Column(name = "conversion_rate", nullable = false)
    private Double conversionRate;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Payments(){}

    public Payments(Payments payments) {
        this.id = payments.id;
        this.invoice = payments.invoice;
        this.invoiceNo = payments.invoiceNo;
        this.amount = payments.amount;
        this.advert = payments.advert;
        this.paymentStatus = payments.paymentStatus;
        this.company = payments.company;
        this.paymentMode = payments.paymentMode;
        this.paymentReference = payments.paymentReference;
        this.payerIdentity = payments.payerIdentity;
        this.conversionRate = payments.conversionRate;
        this.currency = payments.currency;
        this.isDeleted = payments.isDeleted;
        this.deletedAt = payments.deletedAt;
        this.createdBy = payments.createdBy;
        this.updatedBy = payments.updatedBy;
        this.createdAt = payments.createdAt;
        this.updatedAt = payments.updatedAt;
    }

    public void createPayment(CreatePaymentRequest createPaymentRequest){
        this.invoiceNo = createPaymentRequest.getInvoiceNo();
        this.amount = createPaymentRequest.getAmount();
        this.paymentStatus = createPaymentRequest.getPaymentStatus();
        this.paymentReference = createPaymentRequest.getPaymentReference();
        this.paymentMode = createPaymentRequest.getPaymentMode();
        this.payerIdentity = createPaymentRequest.getPayerIdentity();
        this.conversionRate = createPaymentRequest.getConversionRate();
        this.currency = createPaymentRequest.getCurrency();
        this.isDeleted = false; 
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updatePayment(UpdatePaymentRequest updatePaymentRequest){
        if(updatePaymentRequest.getInvoiceNo() != null){
            this.setInvoiceNo(updatePaymentRequest.getInvoiceNo());
        }

        if(updatePaymentRequest.getAmount() != null){
            this.setAmount(updatePaymentRequest.getAmount());
        }

        if(updatePaymentRequest.getPaymentStatus() != null){
            this.setPaymentStatus(updatePaymentRequest.getPaymentStatus());
        }

        if(updatePaymentRequest.getPaymentReference() != null){
            this.setPaymentReference(updatePaymentRequest.getPaymentReference());
        }

        if(updatePaymentRequest.getPaymentMode() != null){
            this.setPaymentMode(updatePaymentRequest.getPaymentMode());
        }

        if(updatePaymentRequest.getPayerIdentity() != null){
            this.setPayerIdentity(updatePaymentRequest.getPayerIdentity());
        }

        if(updatePaymentRequest.getConversionRate() != null){
            this.setConversionRate(updatePaymentRequest.getConversionRate());
        }

        if(updatePaymentRequest.getCurrency() != null){
            this.setCurrency(updatePaymentRequest.getCurrency());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}