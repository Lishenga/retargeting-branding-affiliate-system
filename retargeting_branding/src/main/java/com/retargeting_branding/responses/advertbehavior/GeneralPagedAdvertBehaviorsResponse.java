package com.retargeting_branding.responses.advertbehavior;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.AdvertBehavior;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedAdvertBehaviorsResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1370131138437447003L;
    
    private Integer status;

    private String message;

    private List<AdvertBehavior> data;
}