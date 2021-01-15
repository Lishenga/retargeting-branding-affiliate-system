package com.retargeting_branding.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.enums.Status;
import com.retargeting_branding.requests.website.CreateWebsiteRequest;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Entity
@Table(name = "website")
@Getter @Setter @ToString
public class Website {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Status status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @OneToMany(targetEntity = Impression.class, cascade = CascadeType.ALL)
    @JoinTable(name = "website_impression", joinColumns = @JoinColumn(name = "website_id"), inverseJoinColumns = @JoinColumn(name = "impression_id"))
    @JsonIgnore
    private List<Impression> impression;

    @OneToMany(targetEntity = AdEvents.class, cascade = CascadeType.ALL)
    @JoinTable(name = "website_adevents", joinColumns = @JoinColumn(name = "website_id"), inverseJoinColumns = @JoinColumn(name = "adevents_id"))
    @JsonIgnore
    private List<AdEvents> adevents;

    @OneToMany(targetEntity = Advert.class, cascade = CascadeType.ALL)
    @JoinTable(name = "website_advert", joinColumns = @JoinColumn(name = "website_id"), inverseJoinColumns = @JoinColumn(name = "advert_id"))
    @JsonIgnore
    private List<Advert> advert;

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

    public Website(){}

    public Website(Website website) {
        this.id = website.id;
        this.name = website.name;
        this.url = website.url;
        this.status = website.status;
        this.impression = website.impression;
        this.advert = website.advert;
        this.adevents = website.adevents;
        this.advertiser = website.advertiser;
        this.isDeleted = website.isDeleted;
        this.deletedAt = website.deletedAt;
        this.createdBy = website.createdBy;
        this.updatedBy = website.updatedBy;
        this.createdAt = website.createdAt;
        this.updatedAt = website.updatedAt;
    }

    public void createWebsite(CreateWebsiteRequest createWebsiteRequest){
        this.name = createWebsiteRequest.getName();
        this.url = createWebsiteRequest.getUrl();
        this.status = createWebsiteRequest.getWebsiteStatus();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}