package com.retargeting_branding.responses.advertbehavior;

import java.io.Serializable;

import com.retargeting_branding.models.AdvertBehavior;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GeneralAdvertBehaviorResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2979701275789112900L;
    
    private Integer status;

    private String message;

    private AdvertBehavior data;
}