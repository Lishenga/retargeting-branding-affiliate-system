package com.authorization.authentication.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.authorization.authentication.enums.UsersType;
import com.authorization.authentication.requests.users.RegisterUserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", unique = false)
    private String firstName;

    @Column(name = "last_name", unique = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", nullable = true, unique = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "is_logged_in", nullable = true)
    private Boolean isLoggedIn;

    @OneToMany(targetEntity = Roles.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Roles> roles;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private UsersType userType;

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

    public Users() {
    }

    public Users(Users user) {

        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.phoneNumber = user.phoneNumber;
        this.isLoggedIn = user.isLoggedIn;
        this.userType = user.userType;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
        this.roles = user.roles;
        this.deletedAt = user.deletedAt;
        this.isDeleted = user.isDeleted;
        this.updatedBy = user.updatedBy;
        this.createdBy = user.createdBy;
    }

    public void userCreate(RegisterUserRequest registerUserRequest) {
        this.email = registerUserRequest.getEmail();
        this.password = registerUserRequest.getPassword();
        this.firstName = registerUserRequest.getFirstName();
        this.lastName = registerUserRequest.getLastName();
        this.phoneNumber = registerUserRequest.getPhoneNumber();
        this.isLoggedIn = false;
        this.isDeleted = false;
        this.userType = registerUserRequest.getUserType();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
