package com.retargeting_branding.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.companyprofile.CreateCompanyProfileRequest;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "companyprofile")
@Getter @Setter
public class CompanyProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Company company;

    @OneToMany(targetEntity = Advert.class, cascade = CascadeType.ALL)
    @JoinTable(name = "companyprofile_adverts", joinColumns = @JoinColumn(name = "companyprofile_id"), inverseJoinColumns = @JoinColumn(name = "advert_id"))
    @JsonIgnore
    private List<Advert> advert;

    @OneToMany(targetEntity = Website.class, cascade = CascadeType.ALL)
    @JoinTable(name = "companyprofile_website", joinColumns = @JoinColumn(name = "companyprofile_id"), inverseJoinColumns = @JoinColumn(name = "website_id"))
    @JsonIgnore
    private List<Website> website;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users updatedBy;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CompanyProfile(){}

    public CompanyProfile(CompanyProfile companyProfile){
        this.id = companyProfile.id;
        this.advert = companyProfile.advert;
        this.city = companyProfile.city;
        this.company = companyProfile.company;
        this.country = companyProfile.country;
        this.createdAt = companyProfile.createdAt;
        this.updatedAt = companyProfile.updatedAt;
        this.updatedBy = companyProfile.updatedBy;
        this.createdBy = companyProfile.createdBy;
        this.state = companyProfile.state;
        this.postalCode = companyProfile.postalCode;
        this.street = companyProfile.street;
        this.website = companyProfile.website;
        this.isDeleted = companyProfile.isDeleted;
        this.deletedAt = companyProfile.deletedAt;
    }

    public void createCompanyProfile(CreateCompanyProfileRequest createCompanyProfileRequest){
        this.street = createCompanyProfileRequest.getStreet();
        this.state = createCompanyProfileRequest.getState();
        this.city = createCompanyProfileRequest.getCity();
        this.postalCode = createCompanyProfileRequest.getPostalCode();
        this.country = createCompanyProfileRequest.getCountry();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}