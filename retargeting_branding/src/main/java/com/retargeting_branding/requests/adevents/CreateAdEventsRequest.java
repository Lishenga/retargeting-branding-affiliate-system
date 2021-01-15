package com.retargeting_branding.requests.adevents;

import java.io.Serializable;

import com.retargeting_branding.enums.AdEventsActions;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdEventsRequest implements Serializable {
    
    private static final long serialVersionUID = 203581524640029651L;

    @NotNull(message = "Provide advertId")
    private Long advertId;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    @NotNull(message = "Provide deviceId")
    private String deviceId;

    @NotNull(message = "Provide deviceIp")
    private String deviceIp;

    @NotNull(message = "Provide location")
    private String location;

    @NotNull(message = "Provide browserAgent")
    private String browserAgent;

    @NotNull(message = "Provide adeventsActions")
    private AdEventsActions adeventsActions;

    @NotNull(message = "Provide viewTime")
    private Long viewTime;

    @NotNull(message = "Provide userFingerPrint")
    private String userFingerPrint;

    @NotNull(message = "Provide websiteId")
    private Long websiteId;
}