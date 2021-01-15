package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.payments_messaging.affiliate.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "marketercommissions")
@Getter @Setter @ToString
public class MarketerCommissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "computed_amount", nullable = false)
    private Double computedAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus paymentStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

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

    public MarketerCommissions (){}

    public MarketerCommissions (MarketerCommissions marketerCommissions){
        this.id = marketerCommissions.id;
        this.computedAmount = marketerCommissions.computedAmount;
        this.paymentStatus = marketerCommissions.paymentStatus;
        this.user = marketerCommissions.user;
        this.isDeleted = marketerCommissions.isDeleted;
        this.deletedAt = marketerCommissions.deletedAt;
        this.createdBy = marketerCommissions.createdBy;
        this.updatedBy = marketerCommissions.updatedBy;
        this.createdAt = marketerCommissions.createdAt;
        this.updatedAt = marketerCommissions.updatedAt;
    }
}
