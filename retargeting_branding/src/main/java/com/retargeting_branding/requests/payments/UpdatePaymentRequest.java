package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePaymentRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3432387464673249615L;
    
    private String invoiceNo;

    private Double amount;

    private Long advertId;

    @NotNull(message= "Provide updatedBy")
    private Long updatedBy;

    @NotNull(message= "Provide paymentId")
    private Long paymentId;

    private Long invoiceId;

    private PaymentStatus paymentStatus;

    private Long companyId;

    private PaymentMode paymentMode;

    private String paymentReference;

    private String payerIdentity;

    private String currency;

    private Double conversionRate;
}