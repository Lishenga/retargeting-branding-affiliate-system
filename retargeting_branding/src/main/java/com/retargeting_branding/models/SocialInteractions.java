package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.socialinteractions.CreateSocialInteractionsRequest;
import com.retargeting_branding.requests.socialinteractions.UpdateSocialInteractionsRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "socialinteractions")
@Getter @Setter @ToString
public class SocialInteractions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advert advert;

    @Column(name = "count", nullable = false)
    private Long count;

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

    public SocialInteractions(){}

    public SocialInteractions(SocialInteractions socialInteractions) {
        this.id = socialInteractions.id;
        this.advertiser = socialInteractions.advertiser;
        this.advert = socialInteractions.advert;
        this.count = socialInteractions.count;
        this.isDeleted = socialInteractions.isDeleted;
        this.deletedAt = socialInteractions.deletedAt;
        this.createdBy = socialInteractions.createdBy;
        this.updatedBy = socialInteractions.updatedBy;
        this.createdAt = socialInteractions.createdAt;
        this.updatedAt = socialInteractions.updatedAt;
    }

    public void createSocialInteraction(CreateSocialInteractionsRequest createSocialInteractionsRequest, Advert advert, Advertiser advertiser, Users user){
        this.advertiser = advertiser;
        this.advert = advert;
        this.createdBy = user;
        this.count = createSocialInteractionsRequest.getCount();
        this.updatedBy = user;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateSocialInteractions(UpdateSocialInteractionsRequest updateSocialInteractionsRequest, Advert advert, Advertiser advertiser, Users user){
        this.setAdvertiser(advertiser);
        this.setAdvert(advert);
        this.setCount(updateSocialInteractionsRequest.getCount());
        this.setUpdatedBy(user);
        this.setUpdatedAt(LocalDateTime.now());
    }
}