package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.payments_messaging.affiliate.enums.PaymentMode;
import com.payments_messaging.affiliate.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payment")
@Getter @Setter @ToString
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Invoice invoice;

    @Column(name = "payment_reference", nullable = false)
    private String paymentReference;

    @Column(name = "payment_identity", nullable = false)
    private String paymentIdentity;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "conversion_rate", nullable = false)
    private Double conversionRate;

    @Column(name = "currency", nullable = false)
    private String currency;

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

    public Payment (){}

    public Payment (Payment payment){
        this.id = payment.id;
        this.invoiceNo = payment.invoiceNo;
        this.invoice = payment.invoice;
        this.paymentReference = payment.paymentReference;
        this.paymentIdentity = payment.paymentIdentity;
        this.amount = payment.amount;
        this.conversionRate = payment.conversionRate;
        this.currency = payment.currency;
        this.paymentStatus = payment.paymentStatus;
        this.paymentMode = payment.paymentMode;
        this.isDeleted = payment.isDeleted;
        this.deletedAt = payment.deletedAt;
        this.createdBy = payment.createdBy;
        this.updatedBy = payment.updatedBy;
        this.createdAt = payment.createdAt;
        this.updatedAt = payment.updatedAt;
    }
}
