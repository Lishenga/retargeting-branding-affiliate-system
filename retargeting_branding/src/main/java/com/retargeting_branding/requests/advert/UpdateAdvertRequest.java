package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Mode;
import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdvertRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3756429256416055585L;
    
    @NotNull(message = "Provide advertId")
    private Long advertId;

    private String title;

    private String description;

    private String redirectLink;

    private String mediaUrl;

    private Long advertTypeId;

    private Long advertiserId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    private Status advertStatus;

    private Long companyId;

    private Boolean isPublished;

    private Mode advertMode;

    private Boolean autoRenew;

    private Integer maxAdvertiserLimit;

    private String expirationDateTime;
}