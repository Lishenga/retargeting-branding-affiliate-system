package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payments_messaging.affiliate.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "commissiondepth")
@Getter @Setter @ToString
public class CommissionDepth {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SavedUrl savedUrl;

    @Column(name = "root_id", nullable = true)
    private Long rootId;

    @Column(name = "node_id", nullable = true)
    private Long nodeId;

    @Column(name = "computed_amount", nullable = false)
    private Double computedAmount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private CommissionRate comissionRate;

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

    public CommissionDepth (){}

    public CommissionDepth(CommissionDepth commissionDepth){
        this.id = commissionDepth.id;
        this.savedUrl = commissionDepth.savedUrl;
        this.rootId = commissionDepth.rootId;
        this.nodeId = commissionDepth.nodeId;
        this.computedAmount = commissionDepth.computedAmount;
        this.user = commissionDepth.user;
        this.paymentStatus = commissionDepth.paymentStatus;
        this.comissionRate = commissionDepth.comissionRate;
        this.isDeleted = commissionDepth.isDeleted;
        this.deletedAt = commissionDepth.deletedAt;
        this.createdBy = commissionDepth.createdBy;
        this.updatedBy = commissionDepth.updatedBy;
        this.createdAt = commissionDepth.createdAt;
        this.updatedAt = commissionDepth.updatedAt;
    }
}
