package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.enums.AdEventsActions;
import com.retargeting_branding.requests.adevents.CreateAdEventsRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adevents")
@Getter @Setter @ToString
public class AdEvents {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advert advert;

    @Column(name = "device_id", nullable = true)
    private String deviceId;

    @Column(name = "device_ip", nullable = true)
    private String deviceIp;

    @Column(name = "location", nullable = true)
    private String location;

    @Column(name = "browser_agent", nullable = false)
    private String browserAgent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private AdEventsActions adeventsActions;

    @Column(name = "view_time", nullable = false)
    private Long viewTime;

    @Column(name = "user_finger_print", nullable = true)
    private String userFingerPrint;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Website website;

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

    public AdEvents(){}

    public AdEvents(AdEvents adEvents) {
        this.id = adEvents.id;
        this.advert = adEvents.advert;
        this.deviceId = adEvents.deviceId;
        this.deviceIp = adEvents.deviceIp;
        this.location = adEvents.location;
        this.browserAgent = adEvents.browserAgent;
        this.adeventsActions = adEvents.adeventsActions;
        this.viewTime = adEvents.viewTime;
        this.userFingerPrint = adEvents.userFingerPrint;
        this.website = adEvents.website;
        this.advertiser = adEvents.advertiser;
        this.createdBy = adEvents.createdBy;
        this.updatedBy = adEvents.updatedBy;
        this.isDeleted = adEvents.isDeleted;
        this.deletedAt = adEvents.deletedAt;
        this.createdAt = adEvents.createdAt;
        this.updatedAt = adEvents.updatedAt;
    }

    public void createAdEvent(CreateAdEventsRequest createAdEventsRequest){
        this.deviceId = createAdEventsRequest.getDeviceId();
        this.deviceIp = createAdEventsRequest.getDeviceIp();
        this.location = createAdEventsRequest.getLocation();
        this.browserAgent = createAdEventsRequest.getBrowserAgent();
        this.adeventsActions = createAdEventsRequest.getAdeventsActions();
        this.viewTime = createAdEventsRequest.getViewTime();
        this.userFingerPrint = createAdEventsRequest.getUserFingerPrint();
        this.isDeleted = false;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}