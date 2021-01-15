package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllPaymentByPaymentModeRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -648846026579529209L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide paymentMode")
    private PaymentMode paymentMode;
}