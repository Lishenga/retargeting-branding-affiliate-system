package com.retargeting_branding.requests.website;

import java.io.Serializable;

import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateWebsiteRequest implements Serializable {
 
    /**
	 *
	 */
	private static final long serialVersionUID = -8806762946593587544L;

	private Long websiteId;

    private String name;

    private String url;

    private Long userId;

    private Status websiteStatus;

    private Long advertiser;
}