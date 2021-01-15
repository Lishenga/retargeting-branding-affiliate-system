package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesByPaymentModeForAdvertRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -6234215396051327567L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide paymentMode")
    private PaymentMode paymentMode;

    @NotNull(message = "Provide advertId")
    private Long advertId;
}