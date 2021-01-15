package com.retargeting_branding.requests.advertbehavior;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.DeviceType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertBehaviorRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8961912495576508811L;
    
    @NotNull(message = "Provide displayWindowStartTime")
    private String displayWindowStartTime;

    @NotNull(message = "Provide displayWindowStopTime")
    private String displayWindowStopTime;

    @NotNull(message = "Provide country")
    private String country;

    @NotNull(message = "Provide deviceType")
    private DeviceType deviceType;

    @NotNull(message = "Provide advertId")
    private Long advertId;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;
}