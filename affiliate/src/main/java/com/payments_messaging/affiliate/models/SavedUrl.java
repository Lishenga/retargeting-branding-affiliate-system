package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "savedurl")
@Getter @Setter @ToString
public class SavedUrl {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "real_url", nullable = false)
    private String realUrl;

    @Column(name = "generated_url", nullable = false)
    private String generatedUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private CommissionRate commissionRate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

    @Column(name = "promo_code", nullable = false)
    private String promoCode;

    @Column(name = "advert_id", nullable = false)
    private Long advertId;

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

    public SavedUrl (){}

    public SavedUrl (SavedUrl savedUrl){
        this.id = savedUrl.id;
        this.realUrl = savedUrl.realUrl;
        this.generatedUrl = savedUrl.generatedUrl;
        this.commissionRate = savedUrl.commissionRate;
        this.promoCode = savedUrl.promoCode;
        this.user = savedUrl.user;
        this.advertId = savedUrl.advertId;
        this.isDeleted = savedUrl.isDeleted;
        this.deletedAt = savedUrl.deletedAt;
        this.createdBy = savedUrl.createdBy;
        this.updatedBy = savedUrl.updatedBy;
        this.createdAt = savedUrl.createdAt;
        this.updatedAt = savedUrl.updatedAt;
    }
}
