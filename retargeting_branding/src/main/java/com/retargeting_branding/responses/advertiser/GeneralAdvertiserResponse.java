package com.retargeting_branding.responses.advertiser;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Advertiser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdvertiserResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 6279814444726089301L;

	private Integer status;

    private String message;

    private List <Advertiser> data;
}