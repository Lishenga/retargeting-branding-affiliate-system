package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.requests.AdvertsAdvertiser.CreateAdvertsAdvertiserRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "advertsadvertiser")
@Getter @Setter @ToString
public class AdvertsAdvertiser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advert advert;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Identity identity;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advertiser createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advertiser updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AdvertsAdvertiser(){}

    public AdvertsAdvertiser(AdvertsAdvertiser advertsadvertiser) {
        this.id = advertsadvertiser.id;
        this.advertiser = advertsadvertiser.advertiser;
        this.advert = advertsadvertiser.advert;
        this.paymentStatus = advertsadvertiser.paymentStatus;
        this.isDeleted = advertsadvertiser.isDeleted;
        this.identity = advertsadvertiser.identity;
        this.deletedAt = advertsadvertiser.deletedAt;
        this.createdBy = advertsadvertiser.createdBy;
        this.updatedBy = advertsadvertiser.updatedBy;
        this.createdAt = advertsadvertiser.createdAt;
        this.updatedAt = advertsadvertiser.updatedAt;
    }

    public void createAdvertsAdvertiser(CreateAdvertsAdvertiserRequest createAdvertsAdvertiserRequest, Advertiser advertiser){
        this.advertiser = advertiser;
        this.createdBy = advertiser;
        this.updatedBy = advertiser;
        this.paymentStatus = createAdvertsAdvertiserRequest.getPaymentStatus();
        this.identity = createAdvertsAdvertiserRequest.getIdentity();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}