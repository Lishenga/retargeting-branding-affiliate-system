package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.notification.CreateNotificationRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "notification")
@Getter @Setter @ToString
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users userId;

    @Column(name = "url", nullable = false)
    private String url;

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

    public Notification(){}

    public Notification(Notification notification) {
        this.id = notification.id;
        this.message = notification.message;
        this.userId = notification.userId;
        this.url = notification.url;
        this.isDeleted = notification.isDeleted;
        this.deletedAt = notification.deletedAt;
        this.createdBy = notification.createdBy;
        this.updatedBy = notification.updatedBy;
        this.createdAt = notification.createdAt;
        this.updatedAt = notification.updatedAt;
    }

    public void createNotification(CreateNotificationRequest createNotificationRequest){

        this.message = createNotificationRequest.getMessage();
        this.url = createNotificationRequest.getUrl();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}