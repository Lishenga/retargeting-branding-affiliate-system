package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertTypeCommissionRate;
import com.retargeting_branding.requests.adverttypecommissionrate.CreateAdvertTypeCommissionRateRequest;
import com.retargeting_branding.requests.adverttypecommissionrate.UpdateAdvertTypeCommissionRateRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.adverttypecommissionrate.GeneralAdvertTypeCommissionRateResponse;
import com.retargeting_branding.responses.adverttypecommissionrate.GeneralPagedAdvertTypeCommissionRateResponse;
import com.retargeting_branding.services.AdvertTypeCommissionRateService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adverttypecommissionrate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertTypeCommissionRateController extends CommonMethods {

    @Autowired
    private AdvertTypeCommissionRateService advertTypeCommissionRateService;

    private GeneralPagedAdvertTypeCommissionRateResponse generalPagedAdvertTypeCommissionRateResponse = new GeneralPagedAdvertTypeCommissionRateResponse();

    private GeneralAdvertTypeCommissionRateResponse generalAdvertTypeCommissionRateResponse = new GeneralAdvertTypeCommissionRateResponse();
    
    @RequestMapping(value = "createadverttypecommissionrate", method = RequestMethod.POST)
    public GeneralResponse createAdvertTypeCommissionRate(@RequestBody CreateAdvertTypeCommissionRateRequest createAdvertTypeCommissionRateRequest) throws GeneralExceptionHandler{

        advertTypeCommissionRateService.createAdvertTypeCommissionRate(createAdvertTypeCommissionRateRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladverttypescommissionrates", method = RequestMethod.POST)
    public GeneralPagedAdvertTypeCommissionRateResponse getAllAdvertTypesCommissionRate(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AdvertTypeCommissionRate> adverttypescommissionrate = advertTypeCommissionRateService.getAllAdvertTypesCommissionRate(generalPagedRequest);
        generalPagedAdvertTypeCommissionRateResponse.setData(adverttypescommissionrate);
        generalPagedAdvertTypeCommissionRateResponse.setMessage(this.SUCCESS);
        generalPagedAdvertTypeCommissionRateResponse.setStatus(200);
        return generalPagedAdvertTypeCommissionRateResponse;
    }

    @RequestMapping(value = "deleteadverttypecommissionrate", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvertTypeCommissionRate(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertTypeCommissionRateService.deleteAdvertTypeCommissionRate(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateadverttypecommissionrate", method = RequestMethod.PATCH)
    public GeneralAdvertTypeCommissionRateResponse updateAdvertTypeCommissionRate(@RequestBody UpdateAdvertTypeCommissionRateRequest updateAdvertTypeCommissionRateRequest) throws GeneralExceptionHandler {

        AdvertTypeCommissionRate rate = advertTypeCommissionRateService.updateAdvertTypeCommissionRate(updateAdvertTypeCommissionRateRequest);
        generalAdvertTypeCommissionRateResponse.setData(rate);
        generalAdvertTypeCommissionRateResponse.setMessage(this.SUCCESS);
        generalAdvertTypeCommissionRateResponse.setStatus(200);
        return generalAdvertTypeCommissionRateResponse;
    }

    @RequestMapping(value = "getadverttypecommissionrate", method = RequestMethod.POST)
    public GeneralAdvertTypeCommissionRateResponse getAdvertTypeCommissionRate(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertTypeCommissionRate rate = advertTypeCommissionRateService.getAdvertTypeCommissionRate(generalParticularRequest);
        generalAdvertTypeCommissionRateResponse.setData(rate);
        generalAdvertTypeCommissionRateResponse.setMessage(this.SUCCESS);
        generalAdvertTypeCommissionRateResponse.setStatus(200);
        return generalAdvertTypeCommissionRateResponse;
    }
}