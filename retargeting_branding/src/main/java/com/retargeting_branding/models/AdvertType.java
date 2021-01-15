package com.retargeting_branding.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.adverttype.CreateAdvertTypeRequest;
import com.retargeting_branding.requests.adverttype.UpdateAdvertTypeRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adverttype")
@Getter @Setter @ToString
public class AdvertType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(targetEntity = Advert.class, cascade = CascadeType.ALL)
    @JoinTable(name = "adverttype_advert", joinColumns = @JoinColumn(name = "adverttype_id"), inverseJoinColumns = @JoinColumn(name = "advert_id"))
    @JsonIgnore
    private List<Advert> advert;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AdvertTypeCommissionRate advertTypeCommissionRate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AdvertTypePricing advertTypePricing;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "display_count", nullable = false)
    private Integer displayCount;

    @Column(name = "display_interval", nullable = false)
    private Integer displayInterval;

    @Column(name = "affiliate_commission_rate", nullable = false)
    private Long affiliateCommissionRate;

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

    public AdvertType(){}

    public AdvertType(AdvertType advertType) {
        this.id = advertType.id;
        this.advert = advertType.advert;
        this.advertTypeCommissionRate = advertType.advertTypeCommissionRate;
        this.advertTypePricing = advertType.advertTypePricing;
        this.priority = advertType.priority;
        this.displayCount = advertType.displayCount;
        this.displayInterval = advertType.displayInterval;
        this.affiliateCommissionRate = advertType.affiliateCommissionRate;
        this.isDeleted = advertType.isDeleted;
        this.createdBy = advertType.createdBy;
        this.updatedBy = advertType.updatedBy;
        this.deletedAt = advertType.deletedAt;
        this.createdAt = advertType.createdAt;
        this.updatedAt = advertType.updatedAt;
    }

    public void createAdvertType(CreateAdvertTypeRequest createAdvertTypeRequest){
        this.priority = createAdvertTypeRequest.getPriority();
        this.displayCount = createAdvertTypeRequest.getDisplayCount();
        this.displayInterval = createAdvertTypeRequest.getDisplayInterval();
        this.affiliateCommissionRate = createAdvertTypeRequest.getAffiliateCommissionRate();
        this.isDeleted = false;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void updateAdvertType(UpdateAdvertTypeRequest updateAdvertTypeRequest){
        if(updateAdvertTypeRequest.getPriority() != null){
            this.setPriority(updateAdvertTypeRequest.getPriority());
        }

        if(updateAdvertTypeRequest.getDisplayCount() != null){
            this.setDisplayCount(updateAdvertTypeRequest.getDisplayCount());
        }

        if(updateAdvertTypeRequest.getDisplayInterval() != null){
            this.setDisplayInterval(updateAdvertTypeRequest.getDisplayInterval());
        }

        if(updateAdvertTypeRequest.getAffiliateCommissionRate() != null){
            this.setAffiliateCommissionRate(updateAdvertTypeRequest.getAffiliateCommissionRate());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}