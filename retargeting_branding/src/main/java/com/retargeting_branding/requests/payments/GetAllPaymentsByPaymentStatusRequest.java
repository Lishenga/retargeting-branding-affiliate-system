package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllPaymentsByPaymentStatusRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -3972293617341523866L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide paymentStatus")
    private PaymentStatus paymentStatus;
}