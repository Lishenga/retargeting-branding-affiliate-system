package com.authorization.authentication.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.authorization.authentication.requests.accesslogs.CreateAccessLogsRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "access_logs")
public class AccessLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users user;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "device_ip", nullable = false)
    private String deviceIp;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "browser_agent", nullable = false)
    private String browserAgent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Clients client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Token accessToken;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AccessLogs() {
        // Constructor
    }

    public AccessLogs(AccessLogs accessLogs) {

        this.id = accessLogs.id;
        this.deviceIp = accessLogs.deviceId;
        this.deviceId = accessLogs.deviceId;
        this.location = accessLogs.location;
        this.browserAgent = accessLogs.browserAgent;
        this.user = accessLogs.user;
        this.client = accessLogs.client;
        this.accessToken = accessLogs.accessToken;
        this.createdAt = accessLogs.createdAt;
        this.updatedAt = accessLogs.updatedAt;
        this.deletedAt = accessLogs.deletedAt;
        this.isDeleted = accessLogs.isDeleted;
        this.updatedBy = accessLogs.updatedBy;
        this.createdBy = accessLogs.createdBy;
    }

    public void saveAccessLogs(CreateAccessLogsRequest createAccessLogsRequest) {
        this.deviceIp = createAccessLogsRequest.getDeviceIp();
        this.deviceId = createAccessLogsRequest.getDeviceId();
        this.location = createAccessLogsRequest.getLocation();
        this.browserAgent = createAccessLogsRequest.getBrowserAgent();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public String getBrowserAgent() {
        return this.browserAgent;
    }

    public void setBrowserAgent(String browserAgent) {
        this.browserAgent = browserAgent;
    }

    public Clients getClient() {
        return this.client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Token getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    public Boolean isIsDeleted() {
        return this.isDeleted;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Users getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Users getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", deviceIp='" + getDeviceIp() + "'" +
            ", location='" + getLocation() + "'" +
            ", browserAgent='" + getBrowserAgent() + "'" +
            ", client='" + getClient() + "'" +
            ", accessToken='" + getAccessToken() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}