package com.retargeting_branding.responses.website;

import java.io.Serializable;

import com.retargeting_branding.models.Website;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralWebsiteResponse implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 5698759156600348049L;

	private Integer status;

    private String message;

    private Website data;
}