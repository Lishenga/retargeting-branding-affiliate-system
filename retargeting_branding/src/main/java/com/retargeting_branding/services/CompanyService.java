package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.company.CompanyExceptionHandler;
import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.utils.CommonMethods;
import com.retargeting_branding.requests.company.CreateCompanyRequest;
import com.retargeting_branding.requests.company.GetParticularCompanyRequest;
import com.retargeting_branding.requests.company.UpdateCompanyRequest;
import com.retargeting_branding.requests.companyprofile.CreateCompanyProfileRequest;
import com.retargeting_branding.requests.companyprofile.UpdateCompanyProfileRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends CommonMethods {

    @Autowired
    private CompanyProfileService companyProfileService;
    
    public Company createCompany(CreateCompanyRequest createCompanyRequest) throws CompanyExceptionHandler, GeneralExceptionHandler {

        Optional <Users> admin = usersRepository.findById(createCompanyRequest.getAdmin());

        Optional <Users> createdBy = usersRepository.findById(createCompanyRequest.getCreatedBy());

        if(!admin.isPresent() || !createdBy.isPresent()){
            throw new CompanyExceptionHandler(this.USER_ERROR);
        }

        Company company = new Company();
        company.createCompany(createCompanyRequest);
        company.setAdmin(admin.get());
        company.setCreatedBy(createdBy.get());
        company.setUpdatedBy(createdBy.get());
        companyRepository.save(company);

        CreateCompanyProfileRequest createCompanyProfileRequest = new CreateCompanyProfileRequest();
        createCompanyProfileRequest.setCity(createCompanyRequest.getCity());
        createCompanyProfileRequest.setState(createCompanyRequest.getState());
        createCompanyProfileRequest.setPostalCode(createCompanyRequest.getPostalCode());
        createCompanyProfileRequest.setStreet(createCompanyRequest.getStreet());
        createCompanyProfileRequest.setCountry(createCompanyRequest.getCountry());
        createCompanyProfileRequest.setCompanyId(company.getId());
        createCompanyProfileRequest.setUserId(createdBy.get().getId());

        companyProfileService.createCompanyProfile(createCompanyProfileRequest);
        
        return company;
    }

    public Company updateCompany(UpdateCompanyRequest updateCompanyRequest) throws CompanyExceptionHandler, GeneralExceptionHandler {

        Optional <Company> find = companyRepository.findById(updateCompanyRequest.getCompanyId());

        if(!find.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional <Users> createdBy = usersRepository.findById(updateCompanyRequest.getUpdatedBy());

        if(!createdBy.isPresent()){
            throw new CompanyExceptionHandler(this.USER_ERROR);
        }

        Company company = find.get();
        company.updateCompany(updateCompanyRequest);

        if(updateCompanyRequest.getAdmin() != null){
            Optional <Users> user = usersRepository.findById(updateCompanyRequest.getAdmin());

            if(!user.isPresent()){
                throw new GeneralExceptionHandler("Admin does not exit");
            }

            company.setAdmin(user.get());
        }

        if(updateCompanyRequest.getUpdatedBy() != null){
            Optional <Users> user = usersRepository.findById(updateCompanyRequest.getUpdatedBy());

            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            company.setUpdatedBy(user.get());
        }



        if(updateCompanyRequest.getState() != null){
            UpdateCompanyProfileRequest update = new UpdateCompanyProfileRequest();
            update.setCompanyId(company.getId());
            update.setUpdatedBy(updateCompanyRequest.getUpdatedBy());
            update.setState(updateCompanyRequest.getState());
            companyProfileService.updateCompanyProfile(update);
        }

        if(updateCompanyRequest.getCountry() != null){
            UpdateCompanyProfileRequest update = new UpdateCompanyProfileRequest();
            update.setCompanyId(company.getId());
            update.setUpdatedBy(updateCompanyRequest.getUpdatedBy());
            update.setCountry(updateCompanyRequest.getCountry());
            companyProfileService.updateCompanyProfile(update);
        }

        if(updateCompanyRequest.getCity() != null){
            UpdateCompanyProfileRequest update = new UpdateCompanyProfileRequest();
            update.setCompanyId(company.getId());
            update.setUpdatedBy(updateCompanyRequest.getUpdatedBy());
            update.setCity(updateCompanyRequest.getCity());
            companyProfileService.updateCompanyProfile(update);
        }

        if(updateCompanyRequest.getStreet() != null){
            UpdateCompanyProfileRequest update = new UpdateCompanyProfileRequest();
            update.setCompanyId(company.getId());
            update.setUpdatedBy(updateCompanyRequest.getUpdatedBy());
            update.setStreet(updateCompanyRequest.getStreet());
            companyProfileService.updateCompanyProfile(update);
        }

        if(updateCompanyRequest.getPostalCode() != null){
            UpdateCompanyProfileRequest update = new UpdateCompanyProfileRequest();
            update.setCompanyId(company.getId());
            update.setUpdatedBy(updateCompanyRequest.getUpdatedBy());
            update.setPostalCode(updateCompanyRequest.getPostalCode());
            companyProfileService.updateCompanyProfile(update);
        }

        companyRepository.save(company);
        
        return company;
    }

    public List <Company> getAllCompanies(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Company> companies = companyRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return companies.getContent();
    }

    public void deleteCompany(GeneralDeleteRequest generalDeleteRequest) throws CompanyExceptionHandler {

        Optional <Company> find = companyRepository.findById(generalDeleteRequest.getId());

        if(!find.isPresent()){
            throw new CompanyExceptionHandler(this.COMPANY_ERROR);
        }

        Company company = find.get();
        company.setIsDeleted(true);
        company.setDeletedAt(LocalDateTime.now());
        companyRepository.save(company);
    }

    public Company getParticularCompany(GetParticularCompanyRequest getParticularCompanyRequest) throws CompanyExceptionHandler {

        Optional <Company> company = companyRepository.findById(getParticularCompanyRequest.getId());

        if(!company.isPresent()){
            throw new CompanyExceptionHandler(this.COMPANY_ERROR);
        }

        return company.get();
    }
}