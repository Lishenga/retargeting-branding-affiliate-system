package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertTypePricing;
import com.retargeting_branding.requests.adverttypepricing.CreateAdvertTypePricingRequest;
import com.retargeting_branding.requests.adverttypepricing.GetAllAdvertTypePricingByPricingModeRequest;
import com.retargeting_branding.requests.adverttypepricing.UpdateAdvertTypePricingRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.adverttypepricing.GeneralAdvertTypePricingResponse;
import com.retargeting_branding.responses.adverttypepricing.GeneralPagedAdvertTypePricingResponse;
import com.retargeting_branding.services.AdvertTypePricingService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adverttypepricing")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertTypePricingController extends CommonMethods {

    @Autowired
    private AdvertTypePricingService advertTypePricingService;

    private GeneralPagedAdvertTypePricingResponse generalPagedAdvertTypePricingResponse = new GeneralPagedAdvertTypePricingResponse();

    private GeneralAdvertTypePricingResponse generalAdvertTypePricingResponse = new GeneralAdvertTypePricingResponse();
    
    @RequestMapping(value = "createadverttypepricing", method = RequestMethod.POST)
    public GeneralResponse createAdvertTypePricing(@RequestBody CreateAdvertTypePricingRequest createAdvertTypePricingRequest) throws GeneralExceptionHandler{

        advertTypePricingService.createAdvertTypePricing(createAdvertTypePricingRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladverttypepricings", method = RequestMethod.POST)
    public GeneralPagedAdvertTypePricingResponse getAllAdvertTypePricings(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AdvertTypePricing> adTypePricing = advertTypePricingService.getAllAdvertTypePricings(generalPagedRequest);
        generalPagedAdvertTypePricingResponse.setData(adTypePricing);
        generalPagedAdvertTypePricingResponse.setMessage(this.SUCCESS);
        generalPagedAdvertTypePricingResponse.setStatus(200);
        return generalPagedAdvertTypePricingResponse;
    }

    @RequestMapping(value = "deleteadverttypepricing", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvertTypePricing(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertTypePricingService.deleteAdvertTypePricing(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateadverttypepricing", method = RequestMethod.PATCH)
    public GeneralAdvertTypePricingResponse updateAdvertTypePricing(@RequestBody UpdateAdvertTypePricingRequest updateAdvertTypePricingRequest) throws GeneralExceptionHandler {

        AdvertTypePricing adTypePricing = advertTypePricingService.updateAdvertTypePricing(updateAdvertTypePricingRequest);
        generalAdvertTypePricingResponse.setData(adTypePricing);
        generalAdvertTypePricingResponse.setMessage(this.SUCCESS);
        generalAdvertTypePricingResponse.setStatus(200);
        return generalAdvertTypePricingResponse;
    }

    @RequestMapping(value = "getadverttypepricing", method = RequestMethod.POST)
    public GeneralAdvertTypePricingResponse getAdvertTypePricing(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertTypePricing adTypePricing = advertTypePricingService.getAdvertTypePricing(generalParticularRequest);
        generalAdvertTypePricingResponse.setData(adTypePricing);
        generalAdvertTypePricingResponse.setMessage(this.SUCCESS);
        generalAdvertTypePricingResponse.setStatus(200);
        return generalAdvertTypePricingResponse;
    }

    @RequestMapping(value = "getalladverttypepricingbypricingmode", method = RequestMethod.POST)
    public GeneralPagedAdvertTypePricingResponse getAllAdvertTypePricingByPricingMode(@RequestBody GetAllAdvertTypePricingByPricingModeRequest getAllAdvertTypePricingByPricingModeRequest){

        List <AdvertTypePricing> adTypePricing = advertTypePricingService.getAllAdvertTypePricingByPricingMode(getAllAdvertTypePricingByPricingModeRequest);
        generalPagedAdvertTypePricingResponse.setData(adTypePricing);
        generalPagedAdvertTypePricingResponse.setMessage(this.SUCCESS);
        generalPagedAdvertTypePricingResponse.setStatus(200);
        return generalPagedAdvertTypePricingResponse;
    }
}