package com.authorization.authentication.requests.accesslogs;

import java.io.Serializable;

public class CreateAccessLogsRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 992830087850531299L;

	private String deviceId;

    private String deviceIp;

    private String location;

    private Long user;

    private String browserAgent;

    private Long client;

    private Long token;

    public CreateAccessLogsRequest(String deviceId, String deviceIp, String location, Long user, String browserAgent, Long client, Long token) {
        this.deviceId = deviceId;
        this.deviceIp = deviceIp;
        this.location = location;
        this.user = user;
        this.browserAgent = browserAgent;
        this.client = client;
        this.token = token;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIp() {
        return this.deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getUser() {
        return this.user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getBrowserAgent() {
        return this.browserAgent;
    }

    public void setBrowserAgent(String browserAgent) {
        this.browserAgent = browserAgent;
    }

    public Long getClient() {
        return this.client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getToken() {
        return this.token;
    }

    public void setToken(Long token) {
        this.token = token;
    }
}