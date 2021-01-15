package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "commissionprices")
@Getter @Setter @ToString
public class CommissionPrices {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "depth", nullable = false)
    private Integer depth;

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

    public CommissionPrices (){}

    public CommissionPrices (CommissionPrices commissionPrices){
        this.id = commissionPrices.id;
        this.amount = commissionPrices.amount;
        this.depth = commissionPrices.depth;
        this.comissionRate = commissionPrices.comissionRate;
        this.isDeleted = commissionPrices.isDeleted;
        this.deletedAt = commissionPrices.deletedAt;
        this.createdBy = commissionPrices.createdBy;
        this.updatedBy = commissionPrices.updatedBy;
        this.createdAt = commissionPrices.createdAt;
        this.updatedAt = commissionPrices.updatedAt;
    }
}
