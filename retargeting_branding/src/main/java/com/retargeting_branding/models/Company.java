package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.requests.company.CreateCompanyRequest;
import com.retargeting_branding.requests.company.UpdateCompanyRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "company")
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users admin;

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

    public Company(){}

    public Company(Company company) {

        this.id = company.id;
        this.name = company.name;
        this.accountNo = company.accountNo;
        this.admin = company.admin;
        this.createdAt = company.createdAt;
        this.updatedAt = company.updatedAt;
        this.deletedAt = company.deletedAt;
        this.isDeleted = company.isDeleted;
        this.updatedBy = company.updatedBy;
        this.createdBy = company.createdBy;
    }

    public void createCompany(CreateCompanyRequest createCompanyRequest){
        this.name = createCompanyRequest.getName();
        this.accountNo = createCompanyRequest.getAccountNo();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateCompany(UpdateCompanyRequest updateCompanyRequest){

        if(updateCompanyRequest.getName() != null){
            this.setName(updateCompanyRequest.getName());
        }

        if(updateCompanyRequest.getAccountNo() != null){
            this.setAccountNo(updateCompanyRequest.getAccountNo());
        }

        this.setUpdatedAt(LocalDateTime.now());

    }
}