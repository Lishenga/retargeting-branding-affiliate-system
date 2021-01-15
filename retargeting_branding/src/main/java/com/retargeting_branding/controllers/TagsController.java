package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Tags;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.tags.CreateTagRequest;
import com.retargeting_branding.requests.tags.GetAllTagsByGenerationTypeRequest;
import com.retargeting_branding.requests.tags.GetAllTagsByStatusRequest;
import com.retargeting_branding.requests.tags.GetAllTagsForAdvertRequest;
import com.retargeting_branding.requests.tags.GetAllTagsForImpressionRequest;
import com.retargeting_branding.requests.tags.UpdateTagRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.tags.GeneralPagedTagsResponse;
import com.retargeting_branding.responses.tags.GeneralTagResponse;
import com.retargeting_branding.services.TagsService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagsController extends CommonMethods {

    @Autowired
    private TagsService tagsService;

    private GeneralPagedTagsResponse generalPagedTagsResponse = new GeneralPagedTagsResponse();

    private GeneralTagResponse generalTagResponse = new GeneralTagResponse();
    
    @RequestMapping(value = "createtag", method = RequestMethod.POST)
    public GeneralResponse createTag(@RequestBody CreateTagRequest createTagRequest) throws GeneralExceptionHandler{

        tagsService.createTag(createTagRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getalltags", method = RequestMethod.POST)
    public GeneralPagedTagsResponse getAllTags(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Tags> tag = tagsService.getAllTags(generalPagedRequest);
        generalPagedTagsResponse.setData(tag);
        generalPagedTagsResponse.setMessage(this.SUCCESS);
        generalPagedTagsResponse.setStatus(200);
        return generalPagedTagsResponse;
    }

    @RequestMapping(value = "deletetag", method = RequestMethod.DELETE)
    public GeneralResponse deleteTags(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        tagsService.deleteTag(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updatetag", method = RequestMethod.PUT)
    public GeneralTagResponse updateTag(@RequestBody UpdateTagRequest updateTagRequest) throws GeneralExceptionHandler {

        Tags tag = tagsService.updateTag(updateTagRequest);
        generalTagResponse.setData(tag);
        generalTagResponse.setMessage(this.SUCCESS);
        generalTagResponse.setStatus(200);
        return generalTagResponse;
    }

    @RequestMapping(value = "gettag", method = RequestMethod.POST)
    public GeneralTagResponse getTag(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        Tags tag = tagsService.getTag(generalParticularRequest);
        generalTagResponse.setData(tag);
        generalTagResponse.setMessage(this.SUCCESS);
        generalTagResponse.setStatus(200);
        return generalTagResponse;
    }

    @RequestMapping(value = "getalltagsbystatus", method = RequestMethod.POST)
    public GeneralPagedTagsResponse getAllTagsByStatus(@RequestBody GetAllTagsByStatusRequest getAllTagsByStatusRequest) {

        List <Tags> tag = tagsService.getAllTagsbyStatus(getAllTagsByStatusRequest);
        generalPagedTagsResponse.setData(tag);
        generalPagedTagsResponse.setMessage(this.SUCCESS);
        generalPagedTagsResponse.setStatus(200);
        return generalPagedTagsResponse;
    }

    @RequestMapping(value = "getalltagsbygenerationtype", method = RequestMethod.POST)
    public GeneralPagedTagsResponse getAllTagsByGenerationType(@RequestBody GetAllTagsByGenerationTypeRequest getAllTagsByGenerationTypeRequest) {

        List <Tags> tag = tagsService.getAllTagsbyGenerationType(getAllTagsByGenerationTypeRequest);
        generalPagedTagsResponse.setData(tag);
        generalPagedTagsResponse.setMessage(this.SUCCESS);
        generalPagedTagsResponse.setStatus(200);
        return generalPagedTagsResponse;
    }

    @RequestMapping(value = "getalltagsforadvert", method = RequestMethod.POST)
    public GeneralPagedTagsResponse getAllTagsForAdvert(@RequestBody GetAllTagsForAdvertRequest getAllTagsForAdvertRequest) throws GeneralExceptionHandler{

        List <Tags> tag = tagsService.getAllTagsForAdvert(getAllTagsForAdvertRequest);
        generalPagedTagsResponse.setData(tag);
        generalPagedTagsResponse.setMessage(this.SUCCESS);
        generalPagedTagsResponse.setStatus(200);
        return generalPagedTagsResponse;
    }

    @RequestMapping(value = "getalltagsforimpression", method = RequestMethod.POST)
    public GeneralPagedTagsResponse getAllTagsForImpression(@RequestBody GetAllTagsForImpressionRequest getAllTagsForImpressionRequest) throws GeneralExceptionHandler{

        List <Tags> tag = tagsService.getAllTagsForImpression(getAllTagsForImpressionRequest);
        generalPagedTagsResponse.setData(tag);
        generalPagedTagsResponse.setMessage(this.SUCCESS);
        generalPagedTagsResponse.setStatus(200);
        return generalPagedTagsResponse;
    }
}