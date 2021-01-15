package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "token")
@Getter @Setter @ToString
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String accesstoken;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "time_leave", nullable = false)
    private Long ttl;

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

    public Token() {
        // Constructor
    }

    public Token(Token token) {

        this.id = token.id;
        this.accesstoken = token.accesstoken;
        this.userId = token.userId;
        this.ttl = token.ttl;
        this.createdAt = token.createdAt;
        this.updatedAt = token.updatedAt;
        this.deletedAt = token.deletedAt;
        this.isDeleted = token.isDeleted;
        this.updatedBy = token.updatedBy;
        this.createdBy = token.createdBy;
    }
}