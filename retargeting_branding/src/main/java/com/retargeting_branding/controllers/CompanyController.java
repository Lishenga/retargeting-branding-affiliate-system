package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.company.CompanyExceptionHandler;
import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.company.CreateCompanyRequest;
import com.retargeting_branding.requests.company.GetParticularCompanyRequest;
import com.retargeting_branding.requests.company.UpdateCompanyRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.company.GeneralCompanyResponse;
import com.retargeting_branding.responses.company.GetParticularCompanyResponse;
import com.retargeting_branding.services.CompanyService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController extends CommonMethods {

    @Autowired
    private CompanyService companyService;

    private GeneralCompanyResponse generalCompanyResponse = new GeneralCompanyResponse();

    private GetParticularCompanyResponse getParticularCompanyResponse = new GetParticularCompanyResponse();

    @RequestMapping(value = "createcompany", method = RequestMethod.POST)
    public GeneralResponse createCompany(@RequestBody CreateCompanyRequest createCompanyRequest) throws CompanyExceptionHandler, GeneralExceptionHandler{

        companyService.createCompany(createCompanyRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallcompanies", method = RequestMethod.POST)
    public GeneralCompanyResponse getAllCompanies(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Company> companies = companyService.getAllCompanies(generalPagedRequest);
        generalCompanyResponse.setData(companies);
        generalCompanyResponse.setMessage(this.SUCCESS);
        generalCompanyResponse.setStatus(200);
        return generalCompanyResponse;
    }

    @RequestMapping(value = "deletecompany", method = RequestMethod.DELETE)
    public GeneralResponse deleteCompany(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws CompanyExceptionHandler {
        companyService.deleteCompany(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularcompany", method = RequestMethod.POST)
    public GetParticularCompanyResponse getParticularCompany(@RequestBody GetParticularCompanyRequest getParticularCompanyRequest) throws CompanyExceptionHandler {

        Company company = companyService.getParticularCompany(getParticularCompanyRequest);
        getParticularCompanyResponse.setData(company);
        getParticularCompanyResponse.setMessage(this.SUCCESS);
        getParticularCompanyResponse.setStatus(200);
        return getParticularCompanyResponse;
    }

    @RequestMapping(value = "updatecompany", method = RequestMethod.PUT)
    public GetParticularCompanyResponse updateCompany(@RequestBody UpdateCompanyRequest updateCompanyRequest) throws CompanyExceptionHandler, GeneralExceptionHandler {

        Company company = companyService.updateCompany(updateCompanyRequest);
        getParticularCompanyResponse.setData(company);
        getParticularCompanyResponse.setMessage(this.SUCCESS);
        getParticularCompanyResponse.setStatus(200);
        return getParticularCompanyResponse;
    }
}