package com.retargeting_branding.responses.impression;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Impression;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedImpressionResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8791135759130421033L;
    
    private Integer status;

    private String message;

    private List<Impression> data;
}