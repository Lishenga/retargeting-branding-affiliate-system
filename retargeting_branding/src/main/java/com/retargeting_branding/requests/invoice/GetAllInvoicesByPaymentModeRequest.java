package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesByPaymentModeRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9043941874019381828L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide paymentMode")
    private PaymentMode paymentMode;
}