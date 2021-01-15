package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.retargeting_branding.enums.DeviceType;
import com.retargeting_branding.requests.advertbehavior.CreateAdvertBehaviorRequest;
import com.retargeting_branding.requests.advertbehavior.UpdateAdvertBehaviorRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "advertbehavior")
@Getter @Setter @ToString
public class AdvertBehavior {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "display_window_start_time", nullable = false)
    private String displayWindowStartTime;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Advert advert;

    @Column(name = "display_window_stop_time", nullable = false)
    private String displayWindowStopTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private DeviceType deviceType;

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

    public AdvertBehavior(){}

    public AdvertBehavior(AdvertBehavior advertBehavior) {
        this.id = advertBehavior.id;
        this.displayWindowStartTime = advertBehavior.displayWindowStartTime;
        this.country = advertBehavior.country;
        this.advert = advertBehavior.advert;
        this.displayWindowStopTime = advertBehavior.displayWindowStopTime;
        this.deviceType = advertBehavior.deviceType;
        this.isDeleted = advertBehavior.isDeleted;
        this.createdBy = advertBehavior.createdBy;
        this.updatedBy = advertBehavior.updatedBy;
        this.deletedAt = advertBehavior.deletedAt;
        this.createdAt = advertBehavior.createdAt;
        this.updatedAt = advertBehavior.updatedAt;
    }

    public void createAdvertBehavior(CreateAdvertBehaviorRequest createAdvertBehaviorRequest){

        this.displayWindowStartTime = createAdvertBehaviorRequest.getDisplayWindowStartTime();
        this.displayWindowStopTime = createAdvertBehaviorRequest.getDisplayWindowStopTime();
        this.country = createAdvertBehaviorRequest.getCountry();
        this.deviceType = createAdvertBehaviorRequest.getDeviceType();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateAdvertBehavior(UpdateAdvertBehaviorRequest updateAdvertBehaviorRequest){

        if(updateAdvertBehaviorRequest.getDisplayWindowStartTime() != null){
            this.setDisplayWindowStartTime(updateAdvertBehaviorRequest.getDisplayWindowStartTime());
        }

        if(updateAdvertBehaviorRequest.getDisplayWindowStopTime() != null){
            this.setDisplayWindowStopTime(updateAdvertBehaviorRequest.getDisplayWindowStopTime());
        }

        if(updateAdvertBehaviorRequest.getCountry() != null){
            this.setCountry(updateAdvertBehaviorRequest.getCountry());
        }

        if(updateAdvertBehaviorRequest.getDeviceType() != null){
            this.setDeviceType(updateAdvertBehaviorRequest.getDeviceType());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}