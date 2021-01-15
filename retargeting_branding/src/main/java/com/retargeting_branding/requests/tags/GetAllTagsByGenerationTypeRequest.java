package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Generation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllTagsByGenerationTypeRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -5812375845362505343L;

	@NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide generationType")
    private Generation generationType;
}