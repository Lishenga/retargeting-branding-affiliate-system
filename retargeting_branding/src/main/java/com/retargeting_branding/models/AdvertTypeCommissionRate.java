package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.retargeting_branding.requests.adverttypecommissionrate.CreateAdvertTypeCommissionRateRequest;
import com.retargeting_branding.requests.adverttypecommissionrate.UpdateAdvertTypeCommissionRateRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adverttypecommissionrate")
@Getter @Setter @ToString
public class AdvertTypeCommissionRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_per_click", nullable = false)
    private Double pricePerClick;

    @Column(name = "price_for_user_on_site_time", nullable = false)
    private Double priceForUserOnSiteTime;

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

    public AdvertTypeCommissionRate(){}

    public AdvertTypeCommissionRate(AdvertTypeCommissionRate advertCommissionRate) {
        this.id = advertCommissionRate.id;
        this.pricePerClick = advertCommissionRate.pricePerClick;
        this.priceForUserOnSiteTime = advertCommissionRate.priceForUserOnSiteTime;
        this.isDeleted = advertCommissionRate.isDeleted;
        this.createdBy = advertCommissionRate.createdBy;
        this.updatedBy = advertCommissionRate.updatedBy;
        this.deletedAt = advertCommissionRate.deletedAt;
        this.createdAt = advertCommissionRate.createdAt;
        this.updatedAt = advertCommissionRate.updatedAt;
    }

    public void createAdvertTypeCommissionRate(CreateAdvertTypeCommissionRateRequest createAdvertTypeCommissionRateRequest){
        this.pricePerClick = createAdvertTypeCommissionRateRequest.getPricePerClick();
        this.priceForUserOnSiteTime = createAdvertTypeCommissionRateRequest.getPriceForUserOnSiteTime();
        this.isDeleted = false;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updateAdvertTypeCommissionRate(UpdateAdvertTypeCommissionRateRequest updateAdvertTypeCommissionRateRequest){
        if(updateAdvertTypeCommissionRateRequest.getPriceForUserOnSiteTime() != null){
            this.setPriceForUserOnSiteTime(updateAdvertTypeCommissionRateRequest.getPriceForUserOnSiteTime());
        }

        if(updateAdvertTypeCommissionRateRequest.getPricePerClick() != null){
            this.setPricePerClick(updateAdvertTypeCommissionRateRequest.getPricePerClick());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}