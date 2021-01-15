package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplayTagsOnDeviceRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5774204420727813619L;
    
    private String deviceId;
}