package com.retargeting_branding.requests.advert;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Mode;
import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertRequest implements Serializable{

	/**
	 *
	 */
    private static final long serialVersionUID = 203581524640029661L;
    
    @NotNull(message = "Provide title")
    private String title;

    private String redirectLink;

    private String description;

    private String mediaUrl;

    @NotNull(message = "Provide advertTypeId")
    private Long advertTypeId;

    private ArrayList<Long> advertiserId;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;

    @NotNull(message = "Provide advertStatus")
    private Status advertStatus;

    @NotNull(message = "Provide companyId")
    private Long companyId;

    @NotNull(message = "Provide isPublished")
    private Boolean isPublished;

    @NotNull(message = "Provide advertMode")
    private Mode advertMode;

    @NotNull(message = "Provide autoRenew")
    private Boolean autoRenew;

    @NotNull(message = "Provide maxAdvertiserLimit")
    private Integer maxAdvertiserLimit;

    @NotNull(message = "Provide expirationDateTime")
    private String expirationDateTime;
}