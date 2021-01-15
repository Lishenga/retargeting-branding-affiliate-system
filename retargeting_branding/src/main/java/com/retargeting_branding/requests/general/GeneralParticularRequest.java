package com.retargeting_branding.requests.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralParticularRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -5136555675126030312L;
    
	@NotNull(message = "Provide id")
    private Long id;
}