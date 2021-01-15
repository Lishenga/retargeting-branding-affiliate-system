package com.retargeting_branding.requests.socialinteractions;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateSocialInteractionsRequest implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    @NotNull(message= "Provide advertId")
    private Long advertId;

    @NotNull(message= "Provide count")
    private Long count;

    @NotNull(message= "Provide updatedBy")
    private Long updatedBy;
}