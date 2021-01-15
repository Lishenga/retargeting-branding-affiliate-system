package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePaymentRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8851865077020688340L;
    
    @NotNull(message= "Provide invoiceNo")
    private String invoiceNo;

    @NotNull(message= "Provide amount")
    private Double amount;

    @NotNull(message= "Provide advertId")
    private Long advertId;

    @NotNull(message= "Provide createdBy")
    private Long createdBy;

    @NotNull(message= "Provide invoiceId")
    private Long invoiceId;

    @NotNull(message= "Provide paymentStatus")
    private PaymentStatus paymentStatus;

    @NotNull(message= "Provide companyId")
    private Long companyId;

    @NotNull(message= "Provide paymentMode")
    private PaymentMode paymentMode;

    @NotNull(message= "Provide paymentReference")
    private String paymentReference;

    @NotNull(message= "Provide payerIdentity")
    private String payerIdentity;

    @NotNull(message= "Provide currency")
    private String currency;

    @NotNull(message= "Provide conversionRate")
    private Double conversionRate;
}