package com.retargeting_branding.responses.adverttypepricing;

import java.io.Serializable;

import com.retargeting_branding.models.AdvertTypePricing;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdvertTypePricingResponse implements Serializable{

	/**
	 *
	 */
    private static final long serialVersionUID = -6034727138933373770L;
    
    private Integer status;

    private String message;

    private AdvertTypePricing data;
}