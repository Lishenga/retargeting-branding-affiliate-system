package com.retargeting_branding.responses.adverttypecommissionrate;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.AdvertTypeCommissionRate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedAdvertTypeCommissionRateResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2299980948110613682L;
    
    private Integer status;

    private String message;

    private List<AdvertTypeCommissionRate> data;
}