package com.authorization.authentication.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.authorization.authentication.requests.users.RegisterUserRequest;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter @Setter @ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "postal_code", nullable = true)
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

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

    public Address() {
        // Constructor
    }

    public void saveAddress(Users user, RegisterUserRequest registerUserRequest) {
        this.street = registerUserRequest.getStreet();
        this.city = registerUserRequest.getCity();
        this.state = registerUserRequest.getState();
        this.postalCode = registerUserRequest.getPostalCode();
        this.country = registerUserRequest.getCountry();
        this.user = user;
        this.isDeleted = false;
        this.createdBy = user;
        this.updatedBy = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}