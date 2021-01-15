package com.retargeting_branding.requests.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralDeleteRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -1916392692631904929L;
    
	@NotNull(message = "Provide id")
    private Long id;
}