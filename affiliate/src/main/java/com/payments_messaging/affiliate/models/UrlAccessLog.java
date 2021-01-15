package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.payments_messaging.affiliate.enums.UrlAccessLogType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "urlaccesslog")
@Getter @Setter @ToString
public class UrlAccessLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SavedUrl savedUrl;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "device_ip", nullable = false)
    private String deviceIp;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "browser_agent", nullable = false)
    private String browserAgent;

    @Column(name = "referred_url", nullable = false)
    private String referredUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private UrlAccessLogType urlAccessLogType;

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

    public UrlAccessLog (){}

    public UrlAccessLog (UrlAccessLog urlAccessLog){
        this.id = urlAccessLog.id;
        this.savedUrl = urlAccessLog.savedUrl;
        this.deviceId = urlAccessLog.deviceId;
        this.deviceIp = urlAccessLog.deviceIp;
        this.location = urlAccessLog.location;
        this.browserAgent = urlAccessLog.browserAgent;
        this.referredUrl = urlAccessLog.referredUrl;
        this.urlAccessLogType = urlAccessLog.urlAccessLogType;
        this.isDeleted = urlAccessLog.isDeleted;
        this.deletedAt = urlAccessLog.deletedAt;
        this.createdBy = urlAccessLog.createdBy;
        this.updatedBy = urlAccessLog.updatedBy;
        this.createdAt = urlAccessLog.createdAt;
        this.updatedAt = urlAccessLog.updatedAt;
    }
}
