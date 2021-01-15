package com.retargeting_branding.requests.adverttypepricing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PricingMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertTypePricingRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6836028192635279002L;

    @NotNull(message = "Provide pricingMode")
    private PricingMode pricingMode;

    @NotNull(message = "Provide amount")
    private Double amount;

    @NotNull(message = "Provide period")
    private Integer period;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;
}