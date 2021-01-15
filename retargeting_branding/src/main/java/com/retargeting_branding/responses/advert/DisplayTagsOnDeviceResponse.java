package com.retargeting_branding.responses.advert;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.utils.GetTags;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DisplayTagsOnDeviceResponse implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 7985611532043283467L;

    private Integer status;

    private String message;

    private List<GetTags> data;
}