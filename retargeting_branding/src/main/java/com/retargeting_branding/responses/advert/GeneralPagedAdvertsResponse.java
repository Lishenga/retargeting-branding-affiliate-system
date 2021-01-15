package com.retargeting_branding.responses.advert;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Advert;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GeneralPagedAdvertsResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 2013748023956804061L;

	private Integer status;

    private String message;

    private List<Advert> data;
}