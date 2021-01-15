package com.retargeting_branding.responses.adverttypepricing;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.AdvertTypePricing;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedAdvertTypePricingResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7740401202566109148L;
    
    private Integer status;

    private String message;

    private List<AdvertTypePricing> data;
}