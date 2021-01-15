package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.payments_messaging.affiliate.enums.PaymentMode;
import com.payments_messaging.affiliate.enums.PaymentStatus;

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
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentMode paymentMode;

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

    public Invoice (){}

    public Invoice (Invoice invoice){
        this.id = invoice.id;
        this.invoiceNo = invoice.invoiceNo;
        this.amount = invoice.amount;
        this.user = invoice.user;
        this.paymentStatus = invoice.paymentStatus;
        this.paymentMode = invoice.paymentMode;
        this.isDeleted = invoice.isDeleted;
        this.deletedAt = invoice.deletedAt;
        this.createdBy = invoice.createdBy;
        this.updatedBy = invoice.updatedBy;
        this.createdAt = invoice.createdAt;
        this.updatedAt = invoice.updatedAt;
    }
}
