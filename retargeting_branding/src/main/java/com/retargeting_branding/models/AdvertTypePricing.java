package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.retargeting_branding.enums.PricingMode;
import com.retargeting_branding.requests.adverttypepricing.CreateAdvertTypePricingRequest;
import com.retargeting_branding.requests.adverttypepricing.UpdateAdvertTypePricingRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adverttypepricing")
@Getter @Setter @ToString
public class AdvertTypePricing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PricingMode pricingMode;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "period", nullable = false)
    private Integer period;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users updatedBy;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AdvertTypePricing(){}

    public AdvertTypePricing(AdvertTypePricing advertTypePricing) {
        this.id = advertTypePricing.id;
        this.pricingMode = advertTypePricing.pricingMode;
        this.amount = advertTypePricing.amount;
        this.period = advertTypePricing.period;
        this.isDeleted = advertTypePricing.isDeleted;
        this.createdBy = advertTypePricing.createdBy;
        this.updatedBy = advertTypePricing.updatedBy;
        this.deletedAt = advertTypePricing.deletedAt;
        this.createdAt = advertTypePricing.createdAt;
        this.updatedAt = advertTypePricing.updatedAt;
    }

    public void createAdvertTypePricing(CreateAdvertTypePricingRequest createAdvertTypePricingRequest){
        this.amount = createAdvertTypePricingRequest.getAmount();
        this.pricingMode = createAdvertTypePricingRequest.getPricingMode();
        this.period = createAdvertTypePricingRequest.getPeriod();
        this.isDeleted = false; 
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updateAdvertTypePricing(UpdateAdvertTypePricingRequest updateAdvertTypePricingRequest){
        if(updateAdvertTypePricingRequest.getAmount() != null){
            this.setAmount(updateAdvertTypePricingRequest.getAmount());
        }

        if(updateAdvertTypePricingRequest.getPricingMode() != null){
            this.setPricingMode(updateAdvertTypePricingRequest.getPricingMode());
        }

        if(updateAdvertTypePricingRequest.getPeriod() != null){
            this.setPeriod(updateAdvertTypePricingRequest.getPeriod());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}