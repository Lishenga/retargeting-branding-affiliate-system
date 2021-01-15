package com.retargeting_branding.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.requests.advert.CreateAdvertRequest;
import com.retargeting_branding.requests.advert.DisplayAdvertsOnDeviceRequest;
import com.retargeting_branding.requests.advert.DisplayTagsOnDeviceRequest;
import com.retargeting_branding.requests.advert.GetAdvertRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusForAdvertiserRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusForCompanyRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusRequest;
import com.retargeting_branding.requests.advert.SocialOrAffiliateRequest;
import com.retargeting_branding.requests.advert.UpdateAdvertRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.advert.DisplayAdvertsOnDeviceResponse;
import com.retargeting_branding.responses.advert.DisplayTagsOnDeviceResponse;
import com.retargeting_branding.responses.advert.GeneralAdvertResponse;
import com.retargeting_branding.responses.advert.GeneralPagedAdvertsResponse;
import com.retargeting_branding.services.AdvertService;
import com.retargeting_branding.utils.CommonMethods;
import com.retargeting_branding.utils.DisplayAdverts;
import com.retargeting_branding.utils.GetTags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/advert")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertController extends CommonMethods {

    @Autowired
    private AdvertService advertService;

    private HttpServletResponse response;

    private GeneralAdvertResponse generalAdvertResponse = new GeneralAdvertResponse();

    private DisplayAdvertsOnDeviceResponse displayAdvertsOnDeviceResponse = new DisplayAdvertsOnDeviceResponse();

    private DisplayTagsOnDeviceResponse displayTagsOnDeviceResponse = new DisplayTagsOnDeviceResponse();

    private GeneralPagedAdvertsResponse generalPagedAdvertsResponse = new GeneralPagedAdvertsResponse();

    @RequestMapping(value = "createadvert", method = RequestMethod.POST)
    public GeneralResponse createAdvert(@RequestBody CreateAdvertRequest createAdvertRequest)
            throws GeneralExceptionHandler {

        advertService.createAdvert(createAdvertRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalladverts", method = RequestMethod.POST)
    public GeneralPagedAdvertsResponse getAllAdverts(@RequestBody GeneralPagedRequest generalPagedRequest) {

        List<Advert> adverts = advertService.getAllAdverts(generalPagedRequest);
        generalPagedAdvertsResponse.setData(adverts);
        generalPagedAdvertsResponse.setMessage(this.SUCCESS);
        generalPagedAdvertsResponse.setStatus(200);
        return generalPagedAdvertsResponse;
    }

    @RequestMapping(path = "/{randomString}")
    public void redirect(@PathVariable("randomString") String randomString) {
        try {
            response.sendRedirect(advertService.redirect(randomString));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "getAdvertsForSocialOrAffiliate", method = RequestMethod.POST)
    public DisplayAdvertsOnDeviceResponse getAdvertsForSocialOrAffiliate(@RequestBody SocialOrAffiliateRequest socialOrAffiliateRequest) throws GeneralExceptionHandler {

        List <DisplayAdverts> adverts = advertService.getAdvertsForSocialOrAffiliate(socialOrAffiliateRequest);
        displayAdvertsOnDeviceResponse.setData(adverts);
        displayAdvertsOnDeviceResponse.setMessage(this.SUCCESS);
        displayAdvertsOnDeviceResponse.setStatus(200);
        return displayAdvertsOnDeviceResponse;
    }

    @RequestMapping(value = "displayAdvertsOnDevice", method = RequestMethod.POST)
    public DisplayAdvertsOnDeviceResponse displayAdvertsOnDevice(@RequestBody DisplayAdvertsOnDeviceRequest displayAdvertsOnDeviceRequest) throws GeneralExceptionHandler{

        List <DisplayAdverts> adverts = advertService.displayAdvertsOnDevice(displayAdvertsOnDeviceRequest);
        displayAdvertsOnDeviceResponse.setData(adverts);
        displayAdvertsOnDeviceResponse.setMessage(this.SUCCESS);
        displayAdvertsOnDeviceResponse.setStatus(200);
        return displayAdvertsOnDeviceResponse;
    }

    @RequestMapping(value = "displayTagsOnDevice", method = RequestMethod.POST)
    public DisplayTagsOnDeviceResponse displayTagsOnDevice(@RequestBody DisplayTagsOnDeviceRequest displayTagsOnDeviceRequest){

        List <GetTags> tags = advertService.displayTagsOnDevice(displayTagsOnDeviceRequest);
        displayTagsOnDeviceResponse.setData(tags);
        displayTagsOnDeviceResponse.setMessage(this.SUCCESS);
        displayTagsOnDeviceResponse.setStatus(200);
        return displayTagsOnDeviceResponse;
    }

    @RequestMapping(value = "deleteadvert", method = RequestMethod.DELETE)
    public GeneralResponse deleteAdvert(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        advertService.deleteAdvert(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateadvert", method = RequestMethod.PUT)
    public GeneralAdvertResponse updateAdvert(@RequestBody UpdateAdvertRequest updateAdvertRequest) throws GeneralExceptionHandler {

        Advert advert = advertService.updateAdvert(updateAdvertRequest);
        generalAdvertResponse.setData(advert);
        generalAdvertResponse.setMessage(this.SUCCESS);
        generalAdvertResponse.setStatus(200);
        return generalAdvertResponse;
    }

    @RequestMapping(value = "getadvert", method = RequestMethod.POST)
    public GeneralAdvertResponse getParticularAdvert(@RequestBody GetAdvertRequest getAdvertRequest) throws GeneralExceptionHandler {

        Advert advert = advertService.getAdvert(getAdvertRequest);
        generalAdvertResponse.setData(advert);
        generalAdvertResponse.setMessage(this.SUCCESS);
        generalAdvertResponse.setStatus(200);
        return generalAdvertResponse;
    }

    @RequestMapping(value = "getalladvertsbystatus", method = RequestMethod.POST)
    public GeneralPagedAdvertsResponse getAllAdvertsByStatus(@RequestBody GetAdvertsByStatusRequest getAdvertsByStatusRequest){

        List <Advert> adverts = advertService.getAdvertsByStatus(getAdvertsByStatusRequest);
        generalPagedAdvertsResponse.setData(adverts);
        generalPagedAdvertsResponse.setMessage(this.SUCCESS);
        generalPagedAdvertsResponse.setStatus(200);
        return generalPagedAdvertsResponse;
    }

    @RequestMapping(value = "getalladvertsbystatusforadvertiser", method = RequestMethod.POST)
    public GeneralPagedAdvertsResponse getAllAdvertsByStatusForAdvertiser(@RequestBody GetAdvertsByStatusForAdvertiserRequest getAdvertsByStatusForAdvertiserRequest) throws GeneralExceptionHandler{

        List <Advert> adverts = advertService.getAdvertsByStatusForAdvertiser(getAdvertsByStatusForAdvertiserRequest);
        generalPagedAdvertsResponse.setData(adverts);
        generalPagedAdvertsResponse.setMessage(this.SUCCESS);
        generalPagedAdvertsResponse.setStatus(200);
        return generalPagedAdvertsResponse;
    }

    @RequestMapping(value = "getalladvertsbystatusforcompany", method = RequestMethod.POST)
    public GeneralPagedAdvertsResponse getAllAdvertsByStatusForCompany(@RequestBody GetAdvertsByStatusForCompanyRequest getAdvertsByStatusForCompanyRequest) throws GeneralExceptionHandler{

        List <Advert> adverts = advertService.getAdvertsByStatusForCompany(getAdvertsByStatusForCompanyRequest);
        generalPagedAdvertsResponse.setData(adverts);
        generalPagedAdvertsResponse.setMessage(this.SUCCESS);
        generalPagedAdvertsResponse.setStatus(200);
        return generalPagedAdvertsResponse;
    }
}