package com.authorization.authentication.requests.emails;

import java.io.Serializable;

public class EmailRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -3922220921516136839L;
    
	private String name;
	private String to;
	private String from;
	private String subject;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}