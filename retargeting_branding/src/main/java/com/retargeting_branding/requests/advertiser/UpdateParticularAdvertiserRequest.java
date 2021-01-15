package com.retargeting_branding.requests.advertiser;

import java.io.Serializable;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.Status;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateParticularAdvertiserRequest implements Serializable {
    

    /**
	 *
	 */
	private static final long serialVersionUID = 1993425762698639115L;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    private Long websiteId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @NotNull(message = "Provide userId")
    private Long userId;

    private Identity group;

    private String websiteName;

    private Long referredBy;

    private String websiteUrl;

    private Status websiteStatus;

    private String socialProfiles;
}