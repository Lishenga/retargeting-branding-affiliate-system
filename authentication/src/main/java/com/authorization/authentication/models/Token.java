package com.authorization.authentication.models;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String accesstoken;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

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
        this.user = token.user;
        this.ttl = token.ttl;
        this.createdAt = token.createdAt;
        this.updatedAt = token.updatedAt;
        this.deletedAt = token.deletedAt;
        this.isDeleted = token.isDeleted;
        this.updatedBy = token.updatedBy;
        this.createdBy = token.createdBy;
    }

    public void saveToken(String key, Users users) {

        Calendar calendar = Calendar.getInstance();
        Long expirationTime = calendar.getTimeInMillis() + 86400000;

        this.accesstoken = key;
        this.ttl = expirationTime;
        this.user = users;
        this.isDeleted = false;
        this.updatedBy = users;
        this.createdBy = users;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccesstoken() {
        return this.accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Long getTtl() {
        return this.ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
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
            ", accesstoken='" + getAccesstoken() + "'" +
            ", user='" + getUser() + "'" +
            ", ttl='" + getTtl() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}