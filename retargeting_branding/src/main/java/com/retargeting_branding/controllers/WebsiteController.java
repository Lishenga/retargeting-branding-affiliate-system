package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Website;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.website.CreateWebsiteRequest;
import com.retargeting_branding.requests.website.GetAllWebsitesByStatusForAdvertiserRequest;
import com.retargeting_branding.requests.website.GetAllWebsitesByStatusRequest;
import com.retargeting_branding.requests.website.GetWebsiteRequest;
import com.retargeting_branding.requests.website.UpdateWebsiteRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.website.GeneralPagedWebsiteResponse;
import com.retargeting_branding.responses.website.GeneralWebsiteResponse;
import com.retargeting_branding.services.WebsiteService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/website")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WebsiteController extends CommonMethods {

    @Autowired
    private WebsiteService websiteService;

    private GeneralWebsiteResponse generalWebsiteResponse = new GeneralWebsiteResponse();

    private GeneralPagedWebsiteResponse generalPagedWebsiteResponse = new GeneralPagedWebsiteResponse();
    
    @RequestMapping(value = "createwebsite", method = RequestMethod.POST)
    public GeneralResponse createWebsite(@RequestBody CreateWebsiteRequest createWebsiteRequest) throws GeneralExceptionHandler{

        websiteService.createWebsite(createWebsiteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallwebsites", method = RequestMethod.POST)
    public GeneralPagedWebsiteResponse getAllWebsites(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Website> web = websiteService.getAllWebsites(generalPagedRequest);
        generalPagedWebsiteResponse.setData(web);
        generalPagedWebsiteResponse.setMessage(this.SUCCESS);
        generalPagedWebsiteResponse.setStatus(200);
        return generalPagedWebsiteResponse;
    }

    @RequestMapping(value = "deletewebsite", method = RequestMethod.DELETE)
    public GeneralResponse deleteWebsite(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        websiteService.deleteWebsite(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updatewebsite", method = RequestMethod.PUT)
    public GeneralWebsiteResponse updateWebsite(@RequestBody UpdateWebsiteRequest updateWebsiteRequest) throws GeneralExceptionHandler {

        Website web = websiteService.updateWebsite(updateWebsiteRequest);
        generalWebsiteResponse.setData(web);
        generalWebsiteResponse.setMessage(this.SUCCESS);
        generalWebsiteResponse.setStatus(200);
        return generalWebsiteResponse;
    }

    @RequestMapping(value = "getwebsite", method = RequestMethod.POST)
    public GeneralWebsiteResponse getWebsite(@RequestBody GetWebsiteRequest getWebsiteRequest) throws GeneralExceptionHandler {

        Website web = websiteService.getWebsite(getWebsiteRequest);
        generalWebsiteResponse.setData(web);
        generalWebsiteResponse.setMessage(this.SUCCESS);
        generalWebsiteResponse.setStatus(200);
        return generalWebsiteResponse;
    }

    @RequestMapping(value = "getallwebsitesbystatus", method = RequestMethod.POST)
    public GeneralPagedWebsiteResponse getAllWebsitesByStatus(@RequestBody GetAllWebsitesByStatusRequest getAllWebsitesByStatusRequest) {

        List <Website> web = websiteService.getAllWebsitesByStatus(getAllWebsitesByStatusRequest);
        generalPagedWebsiteResponse.setData(web);
        generalPagedWebsiteResponse.setMessage(this.SUCCESS);
        generalPagedWebsiteResponse.setStatus(200);
        return generalPagedWebsiteResponse;
    }

    @RequestMapping(value = "getallwebsitesbystatusforadvertiser", method = RequestMethod.POST)
    public GeneralPagedWebsiteResponse getAllWebsitesByStatusForAdvertiser(@RequestBody GetAllWebsitesByStatusForAdvertiserRequest getAllWebsitesByStatusForAdvertiserRequest) throws GeneralExceptionHandler{

        List <Website> web = websiteService.getAllWebsitesByStatusForAdvertiser(getAllWebsitesByStatusForAdvertiserRequest);
        generalPagedWebsiteResponse.setData(web);
        generalPagedWebsiteResponse.setMessage(this.SUCCESS);
        generalPagedWebsiteResponse.setStatus(200);
        return generalPagedWebsiteResponse;
    }
}