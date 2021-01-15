package com.retargeting_branding.requests.adverttypepricing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PricingMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllAdvertTypePricingByPricingModeRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2896782012599716766L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide pricingMode")
    private PricingMode pricingMode;
}