package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.promocodes.CreatePromoCodeRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "promocodes")
@Getter @Setter @ToString
public class PromoCodes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @Column(name = "promo_code", nullable = false)
    private String promoCode;

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

    public PromoCodes(){}

    public PromoCodes(PromoCodes promoCodes) {
        this.id = promoCodes.id;
        this.advertiser = promoCodes.advertiser;
        this.promoCode = promoCodes.promoCode;
        this.isDeleted = promoCodes.isDeleted;
        this.deletedAt = promoCodes.deletedAt;
        this.createdBy = promoCodes.createdBy;
        this.updatedBy = promoCodes.updatedBy;
        this.createdAt = promoCodes.createdAt;
        this.updatedAt = promoCodes.updatedAt;
    }

    public void createPromoCode(CreatePromoCodeRequest createPromoCodeRequest, Advertiser advertiser, Users user){

        if(createPromoCodeRequest.getAdvertiserId() != null){
            this.advertiser = advertiser;
            this.promoCode = createPromoCodeRequest.getPromoCode();
            this.createdBy = user;
            this.updatedBy = user;
            this.isDeleted = false;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }else{
            this.promoCode = createPromoCodeRequest.getPromoCode();
            this.createdBy = user;
            this.updatedBy = user;
            this.isDeleted = false;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }
    }
}