package com.retargeting_branding.responses.advert;

import java.io.Serializable;

import com.retargeting_branding.models.Advert;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdvertResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -6664725949336892472L;

	private Integer status;

    private String message;

    private Advert data;
}