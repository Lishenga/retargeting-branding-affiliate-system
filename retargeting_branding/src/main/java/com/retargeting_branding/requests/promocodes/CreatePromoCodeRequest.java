package com.retargeting_branding.requests.promocodes;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePromoCodeRequest implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1461617998612332077L;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    @NotNull(message= "Provide promoCode")
    private String promoCode;

    @NotNull(message= "Provide createdBy")
    private Long createdBy;
}