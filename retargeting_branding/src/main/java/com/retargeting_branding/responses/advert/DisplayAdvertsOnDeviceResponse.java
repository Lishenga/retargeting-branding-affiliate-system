package com.retargeting_branding.responses.advert;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.utils.DisplayAdverts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplayAdvertsOnDeviceResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5087449892211808187L;
    
    private Integer status;

    private String message;

    private List<DisplayAdverts> data;
}