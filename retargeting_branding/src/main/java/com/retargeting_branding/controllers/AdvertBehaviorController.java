package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertBehavior;
import com.retargeting_branding.requests.advertbehavior.CreateAdvertBehaviorRequest;
import com.retargeting_branding.requests.advertbehavior.GetAdvertBehaviorForAdvertRequest;
import com.retargeting_branding.requests.advertbehavior.UpdateAdvertBehaviorRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.advertbehavior.GeneralAdvertBehaviorResponse;
import com.retargeting_branding.responses.advertbehavior.GeneralPagedAdvertBehaviorsResponse;
import com.retargeting_branding.services.AdvertBehaviorService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/advertbehavior")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertBehaviorController extends CommonMethods {

    @Autowired
    private AdvertBehaviorService advertBehaviorService;

    private GeneralPagedAdvertBehaviorsResponse generalPagedAdvertBehaviorsResponse = new GeneralPagedAdvertBehaviorsResponse();

    private GeneralAdvertBehaviorResponse generalAdvertBehaviorResponse = new GeneralAdvertBehaviorResponse();
    
    @RequestMapping(value = "createadvertbehavior", method = RequestMethod.POST)
    public GeneralResponse createAdvertBehavior(@RequestBody CreateAdvertBehaviorRequest createAdvertBehaviorRequest) throws GeneralExceptionHandler{

        advertBehaviorService.createAdvertBehavior(createAdvertBehaviorRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladvertbehaviors", method = RequestMethod.POST)
    public GeneralPagedAdvertBehaviorsResponse getAllAdvertBehaviors(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <AdvertBehavior> adBehave = advertBehaviorService.getAllAdvertBehaviors(generalPagedRequest);
        generalPagedAdvertBehaviorsResponse.setData(adBehave);
        generalPagedAdvertBehaviorsResponse.setMessage(this.SUCCESS);
        generalPagedAdvertBehaviorsResponse.setStatus(200);
        return generalPagedAdvertBehaviorsResponse;
    }

    @RequestMapping(value = "getadvertbehaviorforadvert", method = RequestMethod.POST)
    public GeneralAdvertBehaviorResponse getAdvertBehaviorForAdvert(@RequestBody GetAdvertBehaviorForAdvertRequest getAdvertBehaviorsForAdvertRequest) throws GeneralExceptionHandler{

        AdvertBehavior adBehave = advertBehaviorService.getAdvertBehaviorForAdvert(getAdvertBehaviorsForAdvertRequest);
        generalAdvertBehaviorResponse.setData(adBehave);
        generalAdvertBehaviorResponse.setMessage(this.SUCCESS);
        generalAdvertBehaviorResponse.setStatus(200);
        return generalAdvertBehaviorResponse;
    }

    @RequestMapping(value = "deleteadvertbehavior", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvertBehavior(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertBehaviorService.deleteAdvertBehavior(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getadvertbehavior", method = RequestMethod.POST)
    public GeneralAdvertBehaviorResponse getAdvertBehavior(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        AdvertBehavior adBehave = advertBehaviorService.getAdvertBehavior(generalParticularRequest);
        generalAdvertBehaviorResponse.setData(adBehave);
        generalAdvertBehaviorResponse.setMessage(this.SUCCESS);
        generalAdvertBehaviorResponse.setStatus(200);
        return generalAdvertBehaviorResponse;
    }

    @RequestMapping(value = "updateadvertbehavior", method = RequestMethod.PUT)
    public GeneralAdvertBehaviorResponse updateAdvertBehavior(@RequestBody UpdateAdvertBehaviorRequest updateAdvertBehaviorRequest) throws GeneralExceptionHandler {

        AdvertBehavior adBehave = advertBehaviorService.updateAdvertBehavior(updateAdvertBehaviorRequest);
        generalAdvertBehaviorResponse.setData(adBehave);
        generalAdvertBehaviorResponse.setMessage(this.SUCCESS);
        generalAdvertBehaviorResponse.setStatus(200);
        return generalAdvertBehaviorResponse;
    }
}