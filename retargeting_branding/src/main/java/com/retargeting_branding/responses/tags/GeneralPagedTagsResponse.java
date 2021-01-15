package com.retargeting_branding.responses.tags;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Tags;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedTagsResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 6502307626060237076L;

	private Integer status;

    private String message;

    private List<Tags> data;
}