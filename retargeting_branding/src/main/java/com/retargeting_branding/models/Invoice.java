package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.requests.invoice.CreateInvoiceRequest;
import com.retargeting_branding.requests.invoice.UpdateInvoiceRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "invoice")
@Getter @Setter @ToString
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advert advert;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus invoiceStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentMode paymentMode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AdvertType advertType;

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

    public Invoice(){}

    public Invoice(Invoice invoice) {
        this.id = invoice.id;
        this.invoiceNo = invoice.invoiceNo;
        this.amount = invoice.amount;
        this.advert = invoice.advert;
        this.invoiceStatus = invoice.invoiceStatus;
        this.company = invoice.company;
        this.paymentMode = invoice.paymentMode;
        this.advertType = invoice.advertType;
        this.isDeleted = invoice.isDeleted;
        this.deletedAt = invoice.deletedAt;
        this.createdBy = invoice.createdBy;
        this.updatedBy = invoice.updatedBy;
        this.createdAt = invoice.createdAt;
        this.updatedAt = invoice.updatedAt;
    }

    public void createInvoice(CreateInvoiceRequest createInvoiceRequest, Users user, AdvertType advertType, Advert advert, Company company){
        
        this.invoiceNo = createInvoiceRequest.getInvoiceNo();
        this.amount = createInvoiceRequest.getAmount();
        this.advert = advert;
        this.invoiceStatus = createInvoiceRequest.getInvoiceStatus();
        this.company = company;
        this.paymentMode = createInvoiceRequest.getPaymentMode();
        this.advertType = advertType;
        this.createdBy = user;
        this.isDeleted = false;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();

    }

    public void updateInvoice(UpdateInvoiceRequest updateInvoiceRequest){

        if(updateInvoiceRequest.getInvoiceNo() != null){
            this.setInvoiceNo(updateInvoiceRequest.getInvoiceNo());
        }

        if(updateInvoiceRequest.getAmount() != null){
            this.setAmount(updateInvoiceRequest.getAmount());
        }

        if(updateInvoiceRequest.getInvoiceStatus() != null){
            this.setInvoiceStatus(updateInvoiceRequest.getInvoiceStatus());
        }

        if(updateInvoiceRequest.getPaymentMode() != null){
            this.setPaymentMode(updateInvoiceRequest.getPaymentMode());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}