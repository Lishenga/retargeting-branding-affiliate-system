package com.retargeting_branding.requests.impression;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class CreateImpressionRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -7741103945616354996L;
    
    @NotNull(message = "Provide websiteId")
    private Long websiteId;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;

    @NotNull(message = "Provide deviceIp")
    private String deviceIp;

    @NotNull(message = "Provide deviceId")
    private String deviceId;

    @NotNull(message = "Provide location")
    private String location;

    @NotNull(message = "Provide browserAgent")
    private String browserAgent;

    private String url;

    @NotNull(message = "Provide timeOnPage")
    private Long timeOnPage;

    @NotNull(message = "Provide requestTime")
    private LocalDateTime requestTime;
}