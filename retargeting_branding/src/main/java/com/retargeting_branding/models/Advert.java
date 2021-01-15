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
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.retargeting_branding.enums.Mode;
import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.Status;
import com.retargeting_branding.requests.advert.CreateAdvertRequest;
import com.retargeting_branding.requests.advert.UpdateAdvertRequest;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "advert")
@Getter @Setter @ToString
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "Advert.getDisplayAdverts", procedureName = "getAdverts", parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "deviceType", type = String.class), @StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class), @StoredProcedureParameter(mode = ParameterMode.IN, name = "numberOfAds", type = Integer.class), @StoredProcedureParameter(mode = ParameterMode.IN, name = "identity", type = Identity.class), @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class)})})
public class Advert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "media_url", nullable = true)
    private String mediaUrl;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, mappedBy="advertsAdvertiser")
    @JsonIgnore 
    private Set<Advertiser> advertiser = new HashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private AdvertType advertType;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Status advertStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Company company;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    @OneToMany(targetEntity = Tags.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advert_tags", joinColumns = @JoinColumn(name = "advert_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
    @JsonIgnore
    private List<Tags> tags;

    @OneToMany(targetEntity = AdEvents.class, cascade = CascadeType.ALL)
    @JoinTable(name = "advert_adevents", joinColumns = @JoinColumn(name = "advert_id"), inverseJoinColumns = @JoinColumn(name = "adevents_id"))
    @JsonIgnore
    private List<AdEvents> adEvents;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Mode advertMode;

    @Column(name = "auto_renew", nullable = false)
    private Boolean autoRenew;

    @Column(name = "redirect_link", nullable = true)
    private String redirectLink;

    @Column(name = "actual_link", nullable = true)
    private String actualLink;

    @Column(name = "max_advertiser_limit", nullable = true)
    private Integer maxAdvertiserLimit;

    @Column(name = "expiration_date", nullable = true)
    private String expirationDateTime;

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

    public Advert(){}

    public Advert(Advert advert) {
        this.id = advert.id;
        this.title = advert.title;
        this.description = advert.description;
        this.mediaUrl = advert.mediaUrl;
        this.advertType = advert.advertType;
        this.advertiser = advert.advertiser;
        this.advertStatus = advert.advertStatus;
        this.company = advert.company;
        this.isPublished = advert.isPublished;
        this.tags = advert.tags;
        this.adEvents = advert.adEvents;
        this.advertMode = advert.advertMode;
        this.autoRenew = advert.autoRenew;
        this.redirectLink = advert.redirectLink;
        this.maxAdvertiserLimit = advert.maxAdvertiserLimit;
        this.expirationDateTime = advert.expirationDateTime;
        this.isDeleted = advert.isDeleted;
        this.createdBy = advert.createdBy;
        this.updatedBy = advert.updatedBy;
        this.deletedAt = advert.deletedAt;
        this.createdAt = advert.createdAt;
        this.updatedAt = advert.updatedAt;
    }

    public void createAdvert(CreateAdvertRequest createAdvertRequest, String serverApi){

        String generatedString = RandomStringUtils.randomAlphanumeric(15);

        if(createAdvertRequest.getDescription() != null){
            this.description = createAdvertRequest.getDescription();
        }
        if(createAdvertRequest.getRedirectLink() != null)
            this.redirectLink = serverApi + "api/advert/redirect/" + generatedString + LocalDateTime.now();
            this.actualLink = createAdvertRequest.getRedirectLink();
        this.title = createAdvertRequest.getTitle();
        if(createAdvertRequest.getMediaUrl() != null)
            this.mediaUrl = createAdvertRequest.getMediaUrl();
        this.advertStatus = createAdvertRequest.getAdvertStatus();
        this.isPublished = createAdvertRequest.getIsPublished();
        this.advertMode = createAdvertRequest.getAdvertMode();
        this.autoRenew = createAdvertRequest.getAutoRenew();
        this.maxAdvertiserLimit = createAdvertRequest.getMaxAdvertiserLimit();
        this.expirationDateTime = createAdvertRequest.getExpirationDateTime();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateAdvert(UpdateAdvertRequest updateAdvertRequest){

        if(updateAdvertRequest.getDescription() != null){
            this.setDescription(updateAdvertRequest.getDescription());
        }

        if(updateAdvertRequest.getRedirectLink() != null){
            this.setRedirectLink(updateAdvertRequest.getRedirectLink());
        }

        if(updateAdvertRequest.getMediaUrl() != null){
            this.setMediaUrl(updateAdvertRequest.getMediaUrl());
        }

        if(updateAdvertRequest.getAdvertStatus() != null){
            this.setAdvertStatus(updateAdvertRequest.getAdvertStatus());
        }

        if(updateAdvertRequest.getIsPublished() != null){
            this.setIsPublished(updateAdvertRequest.getIsPublished());
        }

        if(updateAdvertRequest.getAdvertMode() != null){
            this.setAdvertMode(updateAdvertRequest.getAdvertMode());
        }

        if(updateAdvertRequest.getAutoRenew() != null){
            this.setAutoRenew(updateAdvertRequest.getAutoRenew());
        }

        if(updateAdvertRequest.getMaxAdvertiserLimit() != null){
            this.setMaxAdvertiserLimit(updateAdvertRequest.getMaxAdvertiserLimit());
        }

        if(updateAdvertRequest.getExpirationDateTime()!= null){
            this.setExpirationDateTime(updateAdvertRequest.getExpirationDateTime());
        }

        this.setUpdatedAt(LocalDateTime.now());

    }
}