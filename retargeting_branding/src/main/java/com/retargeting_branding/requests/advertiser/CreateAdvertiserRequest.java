package com.retargeting_branding.requests.advertiser;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertiserRequest {
    
    @NotNull(message = "Provide firstName")
    private String firstName;

    @NotNull(message = "Provide lastName")
    private String lastName;

    @NotNull(message = "Provide phoneNumber")
    private String phoneNumber;

    @NotNull(message = "Provide email")
    private String email;

    @NotNull(message = "Provide country")
    private String country;

    @NotNull(message = "Provide userId")
    private Long userId;

    @NotNull(message = "Provide group")
    private Identity group;

    private ArrayList<Long> advertId;

    private Long referredBy;

    private String websiteName;

    private String websiteUrl;

    private Status websiteStatus;

    private String socialProfiles;
}