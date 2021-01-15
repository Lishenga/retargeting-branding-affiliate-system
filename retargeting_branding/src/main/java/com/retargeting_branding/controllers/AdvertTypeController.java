package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertType;
import com.retargeting_branding.requests.adverttype.CreateAdvertTypeRequest;
import com.retargeting_branding.requests.adverttype.UpdateAdvertTypeRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.adverttype.GeneralAdvertTypeResponse;
import com.retargeting_branding.responses.adverttype.GeneralPagedAdvertTypeResponse;
import com.retargeting_branding.services.AdvertTypeService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adverttype")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertTypeController extends CommonMethods {

    @Autowired
    private AdvertTypeService advertTypeService;

    private GeneralPagedAdvertTypeResponse generalPagedAdvertTypeResponse = new GeneralPagedAdvertTypeResponse();

    private GeneralAdvertTypeResponse generalAdvertTypeResponse = new GeneralAdvertTypeResponse();
    
    @RequestMapping(value = "createadverttype", method = RequestMethod.POST)
    public GeneralResponse createAdvertType(@RequestBody CreateAdvertTypeRequest createAdvertTypeRequest) throws GeneralExceptionHandler{

        advertTypeService.createAdvertType(createAdvertTypeRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladverttypes", method = RequestMethod.POST)
    public GeneralPagedAdvertTypeResponse getAllAdvertTypes(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AdvertType> adverttypes = advertTypeService.getAllAdvertTypes(generalPagedRequest);
        generalPagedAdvertTypeResponse.setData(adverttypes);
        generalPagedAdvertTypeResponse.setMessage(this.SUCCESS);
        generalPagedAdvertTypeResponse.setStatus(200);
        return generalPagedAdvertTypeResponse;
    }

    @RequestMapping(value = "deleteadverttype", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvertType(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertTypeService.deleteAdvertType(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateadverttype", method = RequestMethod.PATCH)
    public GeneralAdvertTypeResponse updateAdvertType(@RequestBody UpdateAdvertTypeRequest updateAdvertTypeRequest) throws GeneralExceptionHandler {

        AdvertType adverttype = advertTypeService.updateAdvertType(updateAdvertTypeRequest);
        generalAdvertTypeResponse.setData(adverttype);
        generalAdvertTypeResponse.setMessage(this.SUCCESS);
        generalAdvertTypeResponse.setStatus(200);
        return generalAdvertTypeResponse;
    }

    @RequestMapping(value = "getadverttype", method = RequestMethod.POST)
    public GeneralAdvertTypeResponse getAdvertType(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertType adverttype = advertTypeService.getAdvertType(generalParticularRequest);
        generalAdvertTypeResponse.setData(adverttype);
        generalAdvertTypeResponse.setMessage(this.SUCCESS);
        generalAdvertTypeResponse.setStatus(200);
        return generalAdvertTypeResponse;
    }

    @RequestMapping(value = "getadverttypeforadverttypecommissionrate", method = RequestMethod.POST)
    public GeneralAdvertTypeResponse getAdvertTypeForAdvertTypeCommissionRate(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertType adverttype = advertTypeService.getAdvertTypeForAdvertTypeCommissionRate(generalParticularRequest);
        generalAdvertTypeResponse.setData(adverttype);
        generalAdvertTypeResponse.setMessage(this.SUCCESS);
        generalAdvertTypeResponse.setStatus(200);
        return generalAdvertTypeResponse;
    }

    @RequestMapping(value = "getadverttypeforadverttypepricing", method = RequestMethod.POST)
    public GeneralAdvertTypeResponse getAdvertTypeForAdvertTypePricing(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertType adverttype = advertTypeService.getAdvertTypeForAdvertTypePricing(generalParticularRequest);
        generalAdvertTypeResponse.setData(adverttype);
        generalAdvertTypeResponse.setMessage(this.SUCCESS);
        generalAdvertTypeResponse.setStatus(200);
        return generalAdvertTypeResponse;
    }
}