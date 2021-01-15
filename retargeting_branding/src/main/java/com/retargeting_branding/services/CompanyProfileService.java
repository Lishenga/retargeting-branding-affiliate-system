package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.CompanyProfile;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.companyprofile.CreateCompanyProfileRequest;
import com.retargeting_branding.requests.companyprofile.GetParticularCompanyProfileRequest;
import com.retargeting_branding.requests.companyprofile.UpdateCompanyProfileRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileService extends CommonMethods {
    
    public CompanyProfile createCompanyProfile(CreateCompanyProfileRequest createCompanyProfileRequest) throws GeneralExceptionHandler {

        Optional <Company> company = companyRepository.findById(createCompanyProfileRequest.getCompanyId());

        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional <Users> user = usersRepository.findById(createCompanyProfileRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        CompanyProfile profile = new CompanyProfile();
        profile.createCompanyProfile(createCompanyProfileRequest);
        profile.setCompany(company.get());
        profile.setCreatedBy(user.get());
        profile.setUpdatedBy(user.get());

        companyProfileRepository.save(profile);

        return profile;
    }

    public CompanyProfile updateCompanyProfile(UpdateCompanyProfileRequest updateCompanyProfileRequest) throws GeneralExceptionHandler {

        Optional <Company> company = companyRepository.findById(updateCompanyProfileRequest.getCompanyId());

        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional <CompanyProfile> profile = companyProfileRepository.findByCompany(company.get());

        if(!profile.isPresent()){
            throw new GeneralExceptionHandler(this.companyProfileError);
        }

        CompanyProfile prof = profile.get();

        if(updateCompanyProfileRequest.getCity() != null){
            prof.setCity(updateCompanyProfileRequest.getCity());
        }

        if(updateCompanyProfileRequest.getState() != null){
            prof.setState(updateCompanyProfileRequest.getState());
        }

        if(updateCompanyProfileRequest.getCountry() != null){
            prof.setCountry(updateCompanyProfileRequest.getCountry());
        }

        if(updateCompanyProfileRequest.getStreet() != null){
            prof.setStreet(updateCompanyProfileRequest.getStreet());
        }

        if(updateCompanyProfileRequest.getPostalCode() != null){
            prof.setPostalCode(updateCompanyProfileRequest.getPostalCode());
        }

        if(updateCompanyProfileRequest.getUpdatedBy() != null){
            Optional <Users> user = usersRepository.findById(updateCompanyProfileRequest.getUpdatedBy());

            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            prof.setUpdatedBy(user.get());

        }

        prof.setUpdatedAt(LocalDateTime.now());

        companyProfileRepository.save(prof);

        return prof;
    }

    public List <CompanyProfile> getAllCompanyProfiles(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <CompanyProfile> profile = companyProfileRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return profile.getContent();
    }

    public void deleteCompanyProfile(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {

        Optional <CompanyProfile> find = companyProfileRepository.findById(generalDeleteRequest.getId());

        if(!find.isPresent()){
            throw new GeneralExceptionHandler(this.companyProfileError);
        }

        CompanyProfile profile = find.get();
        profile.setIsDeleted(true);
        profile.setDeletedAt(LocalDateTime.now());
        companyProfileRepository.save(profile);
    }

    public CompanyProfile getParticularCompanyProfile(GetParticularCompanyProfileRequest getParticularCompanyProfileRequest) throws GeneralExceptionHandler {

        Optional <CompanyProfile> prof = companyProfileRepository.findById(getParticularCompanyProfileRequest.getCompanyProfileId());

        if(!prof.isPresent()){
            throw new GeneralExceptionHandler(this.companyProfileError);
        }

        return prof.get();
    }
}