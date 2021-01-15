package com.payments_messaging.affiliate.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "notifications")
@Getter @Setter @ToString
public class Notifications {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "messages", nullable = false)
    private String messages;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

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

    public Notifications (){}

    public Notifications (Notifications notifications){
        this.id = notifications.id;
        this.messages = notifications.messages;
        this.url = notifications.url;
        this.user = notifications.user;
        this.isDeleted = notifications.isDeleted;
        this.deletedAt = notifications.deletedAt;
        this.createdBy = notifications.createdBy;
        this.updatedBy = notifications.updatedBy;
        this.createdAt = notifications.createdAt;
        this.updatedAt = notifications.updatedAt;
    }
}
