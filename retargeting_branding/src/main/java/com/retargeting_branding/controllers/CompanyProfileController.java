package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.CompanyProfile;
import com.retargeting_branding.requests.companyprofile.CreateCompanyProfileRequest;
import com.retargeting_branding.requests.companyprofile.GetParticularCompanyProfileRequest;
import com.retargeting_branding.requests.companyprofile.UpdateCompanyProfileRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.companyprofile.GeneralPagedCompanyProfileResponse;
import com.retargeting_branding.responses.companyprofile.GetParticularCompanyProfileResponse;
import com.retargeting_branding.services.CompanyProfileService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/companyprofile")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyProfileController extends CommonMethods {

    @Autowired
    private CompanyProfileService companyProfileService;

    private GetParticularCompanyProfileResponse getParticularCompanyProfileResponse = new GetParticularCompanyProfileResponse();

    private GeneralPagedCompanyProfileResponse generalPagedCompanyProfileResponse = new GeneralPagedCompanyProfileResponse();
    
    @RequestMapping(value = "createcompanyprofile", method = RequestMethod.POST)
    public GeneralResponse createCompanyProfile(@RequestBody CreateCompanyProfileRequest createCompanyProfileRequest) throws GeneralExceptionHandler{

        companyProfileService.createCompanyProfile(createCompanyProfileRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallcompanyprofiles", method = RequestMethod.POST)
    public GeneralPagedCompanyProfileResponse getAllCompanyProfiles(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <CompanyProfile> profiles = companyProfileService.getAllCompanyProfiles(generalPagedRequest);
        generalPagedCompanyProfileResponse.setData(profiles);
        generalPagedCompanyProfileResponse.setMessage(this.SUCCESS);
        generalPagedCompanyProfileResponse.setStatus(200);
        return generalPagedCompanyProfileResponse;
    }

    @RequestMapping(value = "deletecompanyprofile", method = RequestMethod.DELETE)
    public GeneralResponse deleteCompanyProfile(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        companyProfileService.deleteCompanyProfile(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularcompanyprofile", method = RequestMethod.POST)
    public GetParticularCompanyProfileResponse getParticularCompanyProfile(@RequestBody GetParticularCompanyProfileRequest getParticularCompanyProfileRequest) throws GeneralExceptionHandler {

        CompanyProfile profile = companyProfileService.getParticularCompanyProfile(getParticularCompanyProfileRequest);
        getParticularCompanyProfileResponse.setData(profile);
        getParticularCompanyProfileResponse.setMessage(this.SUCCESS);
        getParticularCompanyProfileResponse.setStatus(200);
        return getParticularCompanyProfileResponse;
    }

    @RequestMapping(value = "updatecompanyprofile", method = RequestMethod.PUT)
    public GetParticularCompanyProfileResponse updateCompanyProfile(@RequestBody UpdateCompanyProfileRequest updateCompanyProfileRequest) throws GeneralExceptionHandler {

        CompanyProfile profile = companyProfileService.updateCompanyProfile(updateCompanyProfileRequest);
        getParticularCompanyProfileResponse.setData(profile);
        getParticularCompanyProfileResponse.setMessage(this.SUCCESS);
        getParticularCompanyProfileResponse.setStatus(200);
        return getParticularCompanyProfileResponse;
    }
}