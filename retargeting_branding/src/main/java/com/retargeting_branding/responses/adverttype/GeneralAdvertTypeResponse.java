package com.retargeting_branding.responses.adverttype;

import java.io.Serializable;

import com.retargeting_branding.models.AdvertType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdvertTypeResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -6569063641729442994L;
    
    private Integer status;

    private String message;

    private AdvertType data;
}