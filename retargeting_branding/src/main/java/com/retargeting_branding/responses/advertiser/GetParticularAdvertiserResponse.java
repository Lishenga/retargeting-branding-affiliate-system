package com.retargeting_branding.responses.advertiser;

import java.io.Serializable;

import com.retargeting_branding.models.Advertiser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularAdvertiserResponse implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 5113813947944830267L;

	private Integer status;

    private String message;

    private Advertiser data;
}