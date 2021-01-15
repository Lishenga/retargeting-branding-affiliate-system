package com.retargeting_branding.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.requests.advertiser.CreateAdvertiserRequest;
import com.retargeting_branding.requests.advertiser.UpdateParticularAdvertiserRequest;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "advertiser")
@Getter @Setter @ToString
public class Advertiser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users referredBy;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "advertiser_adverts", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "advert_id"))
    @JsonIgnore
    private Set<Advert> advertsAdvertiser = new HashSet<>();

    @OneToMany(targetEntity = AdvertTypeCommissionRate.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advertiser_advertcommissionrate", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "advertcommissionrate_id"))
    @JsonIgnore
    private List<AdvertTypeCommissionRate> advertCommissionRate;

    @OneToMany(targetEntity = SocialInteractions.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advertiser_socialinteractions", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "socialinteractions_id"))
    @JsonIgnore
    private List<SocialInteractions> advertiserSocialInteractions;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Identity identity;

    @OneToMany(targetEntity = Website.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advertiser_website", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "website_id"))
    @JsonIgnore
    private List<Website> website;

    @OneToMany(targetEntity = PromoCodes.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advertiser_promocodes", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "promocodes_id"))
    @JsonIgnore
    private List<PromoCodes> promoCodes;

    @OneToMany(targetEntity = Impression.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advertiser_impression", joinColumns = @JoinColumn(name = "advertiser_id"), inverseJoinColumns = @JoinColumn(name = "impression_id"))
    @JsonIgnore
    private List<Impression> impression;

    @Column(name = "social_profiles", nullable = true)
    private String socialProfiles;

    @Column(name = "country", nullable = false)
    private String country;

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

    public Advertiser(){}

    public Advertiser(Advertiser advertiser) {
        this.id = advertiser.id;
        this.firstName = advertiser.firstName;
        this.lastName = advertiser.lastName;
        this.email = advertiser.email;
        this.phoneNumber = advertiser.phoneNumber;
        this.userId = advertiser.userId;
        this.referredBy = advertiser.referredBy;
        this.advertiserSocialInteractions = advertiser.advertiserSocialInteractions;
        this.advertsAdvertiser = advertiser.advertsAdvertiser;
        this.advertCommissionRate = advertiser.advertCommissionRate;
        this.identity = advertiser.identity;
        this.website = advertiser.website;
        this.country = advertiser.country;
        this.impression = advertiser.impression;
        this.promoCodes = advertiser.promoCodes;
        this.socialProfiles = advertiser.socialProfiles;
        this.isDeleted = advertiser.isDeleted;
        this.createdBy = advertiser.createdBy;
        this.updatedBy = advertiser.updatedBy;
        this.deletedAt = advertiser.deletedAt;
        this.createdAt = advertiser.createdAt;
        this.updatedAt = advertiser.updatedAt;
    }

    public void createAdvertiser(CreateAdvertiserRequest createAdvertiserRequest){
        this.firstName = createAdvertiserRequest.getFirstName();
        this.lastName = createAdvertiserRequest.getLastName();
        this.email = createAdvertiserRequest.getEmail();
        this.phoneNumber = createAdvertiserRequest.getPhoneNumber();
        this.identity = createAdvertiserRequest.getGroup();
        this.country = createAdvertiserRequest.getCountry();
        if(createAdvertiserRequest.getSocialProfiles() != null){
            this.socialProfiles = createAdvertiserRequest.getSocialProfiles();
        }
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateAdvertiser(UpdateParticularAdvertiserRequest updateParticularAdvertiserRequest) throws GeneralExceptionHandler{

        if(updateParticularAdvertiserRequest.getFirstName() != null){
            this.setFirstName(updateParticularAdvertiserRequest.getFirstName());
        }

        if(updateParticularAdvertiserRequest.getLastName() != null){
            this.setLastName(updateParticularAdvertiserRequest.getLastName());
        }

        if(updateParticularAdvertiserRequest.getEmail() != null){
            this.setEmail(updateParticularAdvertiserRequest.getEmail());
        }

        if(updateParticularAdvertiserRequest.getPhoneNumber() != null){
            this.setPhoneNumber(updateParticularAdvertiserRequest.getPhoneNumber());
        }

        if(updateParticularAdvertiserRequest.getGroup() != null){
            this.setIdentity(updateParticularAdvertiserRequest.getGroup());
        }

        if(updateParticularAdvertiserRequest.getSocialProfiles()!= null){
            this.setSocialProfiles(updateParticularAdvertiserRequest.getSocialProfiles());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }

}