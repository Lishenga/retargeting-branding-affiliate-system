package com.retargeting_branding.responses.impression;

import java.io.Serializable;

import com.retargeting_branding.models.Impression;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralImpressionResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -7538758699669055334L;

	private Integer status;

    private String message;

    private Impression data;
}