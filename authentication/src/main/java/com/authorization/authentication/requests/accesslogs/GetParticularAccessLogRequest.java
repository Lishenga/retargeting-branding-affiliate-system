package com.authorization.authentication.requests.accesslogs;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularAccessLogRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -1799778027863529303L;
    
    @NotNull(message = "Provide accessLogId")
	private Long accessLogId;

    public Long getAccessLogId() {
        return this.accessLogId;
    }

    public void setAccessLogId(Long accessLogId) {
        this.accessLogId = accessLogId;
    }    
}