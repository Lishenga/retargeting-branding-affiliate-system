package com.retargeting_branding.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.impression.CreateImpressionRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "impression")
@Getter @Setter @ToString
public class Impression {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Website website;

    @Column(name = "device_id", nullable = true)
    private String deviceId;

    @Column(name = "device_ip", nullable = false)
    private String deviceIp;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "browser_agent", nullable = false)
    private String browserAgent;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "time_on_page", nullable = false)
    private Long timeOnPage;

    @Column(name = "request_time", nullable = true)
    private LocalDateTime requestTime;

    @OneToMany(targetEntity = Tags.class, cascade = CascadeType.ALL)
    @JoinTable(name = "impression_tags", joinColumns = @JoinColumn(name = "impression_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
    @JsonIgnore
    private List<Tags> tags;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advertiser advertiser;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advertiser createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advertiser updatedBy;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Impression(){}

    public Impression(Impression impression) {
        this.id = impression.id;
        this.website = impression.website;
        this.deviceId = impression.deviceId;
        this.deviceIp = impression.deviceIp;
        this.location = impression.location;
        this.browserAgent = impression.browserAgent;
        this.url = impression.url;
        this.timeOnPage = impression.timeOnPage;
        this.requestTime = impression.requestTime;
        this.tags = impression.tags;
        this.advertiser = impression.advertiser;
        this.isDeleted = impression.isDeleted;
        this.createdBy = impression.createdBy;
        this.updatedBy = impression.updatedBy;
        this.deletedAt = impression.deletedAt;
        this.createdAt = impression.createdAt;
        this.updatedAt = impression.updatedAt;
    }

    public void createImpression(CreateImpressionRequest createImpressionRequest){
        this.deviceId = createImpressionRequest.getDeviceId();
        this.deviceIp = createImpressionRequest.getDeviceIp();
        this.location = createImpressionRequest.getLocation();
        this.browserAgent = createImpressionRequest.getBrowserAgent();
        this.timeOnPage = createImpressionRequest.getTimeOnPage();
        this.requestTime = createImpressionRequest.getRequestTime();
        this.isDeleted = false;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}