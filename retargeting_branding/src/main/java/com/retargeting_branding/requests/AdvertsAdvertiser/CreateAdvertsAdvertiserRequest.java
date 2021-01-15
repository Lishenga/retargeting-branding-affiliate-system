package com.retargeting_branding.requests.AdvertsAdvertiser;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertsAdvertiserRequest implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -5772029736959052294L;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    @NotNull(message = "Provide advertId")
    private Long advertId;

    @NotNull(message= "Provide paymentStatus")
    private PaymentStatus paymentStatus;

    @NotNull(message= "Provide identity")
    private Identity identity;

    @NotNull(message= "Provide createdBy")
    private Long createdBy;
}