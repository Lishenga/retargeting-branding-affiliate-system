package com.retargeting_branding.responses.payments;

import java.io.Serializable;

import com.retargeting_branding.models.Payments;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GeneralPaymentResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 8335265274914019588L;
    
    private Integer status;

    private String message;

    private Payments data;
}