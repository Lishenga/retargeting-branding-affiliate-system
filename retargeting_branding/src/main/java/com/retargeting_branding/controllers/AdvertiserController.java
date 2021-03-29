package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.requests.advertiser.CreateAdvertiserRequest;
import com.retargeting_branding.requests.advertiser.GetParticularAdvertiserRequest;
import com.retargeting_branding.requests.advertiser.UpdateParticularAdvertiserRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.advertiser.GeneralAdvertiserResponse;
import com.retargeting_branding.responses.advertiser.GetParticularAdvertiserResponse;
import com.retargeting_branding.services.AdvertiserService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/advertiser")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertiserController extends CommonMethods {

    @Autowired
    private AdvertiserService advertiserService;

    private GeneralAdvertiserResponse generalAdvertiserResponse = new GeneralAdvertiserResponse();

    private GetParticularAdvertiserResponse getParticularAdvertiserResponse = new GetParticularAdvertiserResponse();

    @RequestMapping(value = "createadvertiser", method = RequestMethod.POST)
    public GeneralResponse createAdvertiser(@RequestBody CreateAdvertiserRequest createAdvertiserRequest) throws GeneralExceptionHandler{

        advertiserService.createAdvertiser(createAdvertiserRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladvertisers", method = RequestMethod.POST)
    public GeneralAdvertiserResponse getAllAdvertisers(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Advertiser> advertisers = advertiserService.getAllAdvertisers(generalPagedRequest);
        generalAdvertiserResponse.setData(advertisers);
        generalAdvertiserResponse.setMessage(this.SUCCESS);
        generalAdvertiserResponse.setStatus(200);
        return generalAdvertiserResponse;
    }

    @RequestMapping(value = "deleteadvertiser", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvertiser(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertiserService.deleteAdvertiser(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateparticularadvertiser", method = RequestMethod.PUT)
    public GetParticularAdvertiserResponse updateParticularAdvertiser(@RequestBody UpdateParticularAdvertiserRequest updateParticularAdvertiserRequest) throws GeneralExceptionHandler {

        Advertiser advertiser = advertiserService.updateParticularAdvertiser(updateParticularAdvertiserRequest);
        getParticularAdvertiserResponse.setData(advertiser);
        getParticularAdvertiserResponse.setMessage(this.SUCCESS);
        getParticularAdvertiserResponse.setStatus(200);
        return getParticularAdvertiserResponse;
    }

    @RequestMapping(value = "getparticularadvertiser", method = RequestMethod.POST)
    public GetParticularAdvertiserResponse getParticularAdvertiser(@RequestBody GetParticularAdvertiserRequest getParticularAdvertiserRequest) throws GeneralExceptionHandler {

        Advertiser advertiser = advertiserService.getParticularAdvertiser(getParticularAdvertiserRequest);
        getParticularAdvertiserResponse.setData(advertiser);
        getParticularAdvertiserResponse.setMessage(this.SUCCESS);
        getParticularAdvertiserResponse.setStatus(200);
        return getParticularAdvertiserResponse;
    }
}