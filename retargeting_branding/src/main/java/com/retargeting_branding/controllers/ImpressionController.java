package com.retargeting_branding.controllers;

import java.net.MalformedURLException;
import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Impression;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.impression.CreateImpressionRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.impression.GeneralImpressionResponse;
import com.retargeting_branding.responses.impression.GeneralPagedImpressionResponse;
import com.retargeting_branding.requests.impression.GetAllImpressionsForAdvertiserRequest;
import com.retargeting_branding.requests.impression.GetAllImpressionsForWebsiteRequest;
import com.retargeting_branding.services.ImpressionService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/impression")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImpressionController extends CommonMethods {

    @Autowired
    private ImpressionService impressionService;

    private GeneralPagedImpressionResponse generalPagedImpressionResponse = new GeneralPagedImpressionResponse();

    private GeneralImpressionResponse generalImpressionResponse = new GeneralImpressionResponse();

    @RequestMapping(value = "createimpression", method = RequestMethod.POST)
    public GeneralResponse createImpression(@RequestBody CreateImpressionRequest createImpressionRequest)
            throws GeneralExceptionHandler, MalformedURLException {

        impressionService.createImpression(createImpressionRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallimpressions", method = RequestMethod.POST)
    public GeneralPagedImpressionResponse getAllImpressions(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Impression> impress = impressionService.getAllImpressions(generalPagedRequest);
        generalPagedImpressionResponse.setData(impress);
        generalPagedImpressionResponse.setMessage(this.SUCCESS);
        generalPagedImpressionResponse.setStatus(200);
        return generalPagedImpressionResponse;
    }

    @RequestMapping(value = "deleteimpression", method = RequestMethod.DELETE)
    public GeneralResponse deleteImpression(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        impressionService.deleteImpression(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getimpression", method = RequestMethod.POST)
    public GeneralImpressionResponse getImpression(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        Impression impress = impressionService.getImpression(generalParticularRequest);
        generalImpressionResponse.setData(impress);
        generalImpressionResponse.setMessage(this.SUCCESS);
        generalImpressionResponse.setStatus(200);
        return generalImpressionResponse;
    }

    @RequestMapping(value = "getallimpressionsforadvertiser", method = RequestMethod.POST)
    public GeneralPagedImpressionResponse getAllImpressionsForAdvertiser(@RequestBody GetAllImpressionsForAdvertiserRequest getAllImpressionsForAdvertiserRequest) throws GeneralExceptionHandler{

        List <Impression> impress = impressionService.getAllImpressionsForAdvertiser(getAllImpressionsForAdvertiserRequest);
        generalPagedImpressionResponse.setData(impress);
        generalPagedImpressionResponse.setMessage(this.SUCCESS);
        generalPagedImpressionResponse.setStatus(200);
        return generalPagedImpressionResponse;
    }

    @RequestMapping(value = "getallimpressionsforwebsite", method = RequestMethod.POST)
    public GeneralPagedImpressionResponse getAllImpressionsForWebsite(@RequestBody GetAllImpressionsForWebsiteRequest getAllImpressionsForWebsiteRequest) throws GeneralExceptionHandler{

        List <Impression> impress = impressionService.getAllImpressionsForWebsite(getAllImpressionsForWebsiteRequest);
        generalPagedImpressionResponse.setData(impress);
        generalPagedImpressionResponse.setMessage(this.SUCCESS);
        generalPagedImpressionResponse.setStatus(200);
        return generalPagedImpressionResponse;
    }
}