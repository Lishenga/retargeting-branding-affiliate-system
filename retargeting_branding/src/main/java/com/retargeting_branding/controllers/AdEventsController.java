package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdEvents;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.adevents.GeneralAdEventsResponse;
import com.retargeting_branding.responses.adevents.GeneralPagedAdEventsResponse;
import com.retargeting_branding.services.AdEventsService;
import com.retargeting_branding.utils.CommonMethods;
import com.retargeting_branding.requests.adevents.CreateAdEventsRequest;
import com.retargeting_branding.requests.adevents.GetAllAdEventsForAdvertiserRequest;
import com.retargeting_branding.requests.adevents.GetAllAdEventsForWebsiteRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adevents")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdEventsController extends CommonMethods {

    @Autowired
    private AdEventsService adEventsService;

    private GeneralPagedAdEventsResponse generalPagedAdEventsResponse = new GeneralPagedAdEventsResponse();

    private GeneralAdEventsResponse generalAdEventsResponse = new GeneralAdEventsResponse();
    
    @RequestMapping(value = "createadevents", method = RequestMethod.POST)
    public GeneralResponse createWebsite(@RequestBody CreateAdEventsRequest createAdEventsRequest) throws GeneralExceptionHandler{

        adEventsService.createAdEvent(createAdEventsRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladevents", method = RequestMethod.POST)
    public GeneralPagedAdEventsResponse getAllAdEvents(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AdEvents> adevent = adEventsService.getAllAdEvents(generalPagedRequest);
        generalPagedAdEventsResponse.setData(adevent);
        generalPagedAdEventsResponse.setMessage(this.SUCCESS);
        generalPagedAdEventsResponse.setStatus(200);
        return generalPagedAdEventsResponse;
    }

    @RequestMapping(value = "deleteadevent", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdEvent(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        adEventsService.deleteAdEvent(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getadevent", method = RequestMethod.POST)
    public GeneralAdEventsResponse getAdEvent(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdEvents adevent = adEventsService.getAdEvent(generalParticularRequest);
        generalAdEventsResponse.setData(adevent);
        generalAdEventsResponse.setMessage(this.SUCCESS);
        generalAdEventsResponse.setStatus(200);
        return generalAdEventsResponse;
    }

    @RequestMapping(value = "getalladeventsforadvertiser", method = RequestMethod.POST)
    public GeneralPagedAdEventsResponse getAllAdEventsForAdvertiser(@RequestBody GetAllAdEventsForAdvertiserRequest getAllAdEventsForAdvertiserRequest) throws GeneralExceptionHandler{

        List <AdEvents> adevents = adEventsService.getAllAdEventsForAdvertiser(getAllAdEventsForAdvertiserRequest);
        generalPagedAdEventsResponse.setData(adevents);
        generalPagedAdEventsResponse.setMessage(this.SUCCESS);
        generalPagedAdEventsResponse.setStatus(200);
        return generalPagedAdEventsResponse;
    }

    @RequestMapping(value = "getalladeventsforwebsite", method = RequestMethod.POST)
    public GeneralPagedAdEventsResponse getAllAdEventsForWebsite(@RequestBody GetAllAdEventsForWebsiteRequest getAllAdEventsForWebsiteRequest) throws GeneralExceptionHandler{

        List <AdEvents> adevents = adEventsService.getAllAdEventsForWebsite(getAllAdEventsForWebsiteRequest);
        generalPagedAdEventsResponse.setData(adevents);
        generalPagedAdEventsResponse.setMessage(this.SUCCESS);
        generalPagedAdEventsResponse.setStatus(200);
        return generalPagedAdEventsResponse;
    }
}