package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplayAdvertsOnDeviceRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7759449101772443393L;
    
    private String country;

    private String deviceType;

    private String deviceId;

    private Long websiteId;
}