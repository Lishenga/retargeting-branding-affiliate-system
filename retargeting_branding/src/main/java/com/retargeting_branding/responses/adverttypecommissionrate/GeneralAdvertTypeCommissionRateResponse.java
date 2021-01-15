package com.retargeting_branding.responses.adverttypecommissionrate;

import java.io.Serializable;

import com.retargeting_branding.models.AdvertTypeCommissionRate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdvertTypeCommissionRateResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9196194322527578538L;
    
    private Integer status;

    private String message;

    private AdvertTypeCommissionRate data;
}