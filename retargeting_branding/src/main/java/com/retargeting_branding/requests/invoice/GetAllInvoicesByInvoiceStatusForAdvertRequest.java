package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentStatus;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GetAllInvoicesByInvoiceStatusForAdvertRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7842733613420699049L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide invoiceStatus")
    private PaymentStatus invoiceStatus;

    @NotNull(message = "Provide advertId")
    private Long advertId;
}