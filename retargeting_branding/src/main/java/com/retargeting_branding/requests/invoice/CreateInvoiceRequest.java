package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateInvoiceRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 567036408212145480L;
    
    @NotNull(message = "Provide invoiceNo")
    private String invoiceNo;

    @NotNull(message = "Provide amount")
    private Double amount;

    @NotNull(message = "Provide advertId")
    private Long advertId;

    @NotNull(message = "Provide invoiceStatus")
    private PaymentStatus invoiceStatus;

    @NotNull(message = "Provide companyId")
    private Long companyId;

    @NotNull(message = "Provide paymentMode")
    private PaymentMode paymentMode;

    @NotNull(message = "Provide advertTypeId")
    private Long advertTypeId;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;
}