package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.models.Website;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.website.CreateWebsiteRequest;
import com.retargeting_branding.requests.website.GetAllWebsitesByStatusForAdvertiserRequest;
import com.retargeting_branding.requests.website.GetAllWebsitesByStatusRequest;
import com.retargeting_branding.requests.website.GetWebsiteRequest;
import com.retargeting_branding.requests.website.UpdateWebsiteRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService extends CommonMethods {

    public void createWebsite(CreateWebsiteRequest createWebsiteRequest) throws GeneralExceptionHandler {

        Optional <Users> user = usersRepository.findById(createWebsiteRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Optional <Advertiser> advertiser = advertiserRepository.findById(createWebsiteRequest.getAdvertiser());

        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Website web = new Website();
        web.createWebsite(createWebsiteRequest);
        web.setAdvertiser(advertiser.get());
        web.setCreatedBy(user.get());
        web.setUpdatedBy(user.get());

        websiteRepository.save(web);
    }

    public Website updateWebsite(UpdateWebsiteRequest updateWebsiteRequest) throws GeneralExceptionHandler {

        Optional <Website> web = websiteRepository.findById(updateWebsiteRequest.getWebsiteId());

        if(!web.isPresent()){
            throw new GeneralExceptionHandler(this.websiteError);
        }

        Optional <Advertiser> advertiser = advertiserRepository.findById(updateWebsiteRequest.getAdvertiser());

        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Optional <Users> user = usersRepository.findById(updateWebsiteRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Website website = web.get();

        if(updateWebsiteRequest.getAdvertiser() != null){
            website.setAdvertiser(advertiser.get());
        }

        if(updateWebsiteRequest.getName() != null){
            website.setName(updateWebsiteRequest.getName());
        }

        if(updateWebsiteRequest.getUrl() != null){
            website.setUrl(updateWebsiteRequest.getUrl());
        }

        if(updateWebsiteRequest.getWebsiteStatus() != null){
            website.setStatus(updateWebsiteRequest.getWebsiteStatus());
        }

        if(updateWebsiteRequest.getUserId() != null){
            website.setUpdatedBy(user.get());
        }

        website.setUpdatedAt(LocalDateTime.now());

        websiteRepository.save(website);

        return website;
    }

    public List<Website> getAllWebsites(GeneralPagedRequest generalPagedRequest){

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Website> web = websiteRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return web.getContent();
    }

    public void deleteWebsite(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{
        
        Optional <Website> web = websiteRepository.findById(generalDeleteRequest.getId());
        if(!web.isPresent()){
            throw new GeneralExceptionHandler(this.websiteError);
        }

        Website website = web.get();
        website.setIsDeleted(true);
        website.setDeletedAt(LocalDateTime.now());
        websiteRepository.save(website);
    }

    public Website getWebsite(GetWebsiteRequest getWebsiteRequest)throws GeneralExceptionHandler{

        Optional <Website> website = websiteRepository.findById(getWebsiteRequest.getWebsiteId());
        if(!website.isPresent()){
            throw new GeneralExceptionHandler(this.websiteError);
        }

        return website.get();
    }

    public List<Website> getAllWebsitesByStatus(GetAllWebsitesByStatusRequest getAllWebsitesByStatusRequest){

        Pageable find = PageRequest.of(getAllWebsitesByStatusRequest.getPage(), getAllWebsitesByStatusRequest.getItems());
        Page <Website> web = websiteRepository.findByStatus(getAllWebsitesByStatusRequest.getWebsiteStatus(), find);

        return web.getContent();
    }

    public List<Website>getAllWebsitesByStatusForAdvertiser(GetAllWebsitesByStatusForAdvertiserRequest getAllWebsitesByStatusForAdvertiserRequest) throws GeneralExceptionHandler{

        Optional <Advertiser> advertiser = advertiserRepository.findById(getAllWebsitesByStatusForAdvertiserRequest.getAdvertiserId());
        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Pageable find = PageRequest.of(getAllWebsitesByStatusForAdvertiserRequest.getPage(), getAllWebsitesByStatusForAdvertiserRequest.getItems());
        Page <Website> web = websiteRepository.findByStatusAndAdvertiser(getAllWebsitesByStatusForAdvertiserRequest.getAdvertStatus(), advertiser.get(), find);

        return web.getContent();
    }
}