package com.retargeting_branding.requests.advertiser;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularAdvertiserRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 8725861380175565430L;
    
	@NotNull(message = "Provide advertiserId")
    private Long advertiserId;
}