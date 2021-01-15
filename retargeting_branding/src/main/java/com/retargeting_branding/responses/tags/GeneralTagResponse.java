package com.retargeting_branding.responses.tags;

import java.io.Serializable;

import com.retargeting_branding.models.Tags;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralTagResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6123236252276877718L;
    
    private Integer status;

    private String message;

    private Tags data;
}