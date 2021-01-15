package com.retargeting_branding.requests.advertbehavior;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.DeviceType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdvertBehaviorRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 7650446238735307329L;

    @NotNull(message = "Provide advertBehaviorId")
    private Long advertBehaviorId;

    private String displayWindowStartTime;

    private String displayWindowStopTime;

    private String country;

    private DeviceType deviceType;

    private Long advertId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;
}