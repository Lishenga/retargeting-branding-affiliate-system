package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Identity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SocialOrAffiliateRequest implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -4119477050058244942L;

    @NotNull(message = "Provide numberOfAds")
    private Integer numberOfAds;

    @NotNull(message = "Provide id")
    private Long id;

    @NotNull(message = "Provide identity")
    private Identity identity;
}