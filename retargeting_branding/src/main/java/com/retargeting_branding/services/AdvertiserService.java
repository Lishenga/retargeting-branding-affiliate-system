package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.advertiser.CreateAdvertiserRequest;
import com.retargeting_branding.requests.advertiser.GetParticularAdvertiserRequest;
import com.retargeting_branding.requests.advertiser.UpdateParticularAdvertiserRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.website.CreateWebsiteRequest;
import com.retargeting_branding.requests.website.UpdateWebsiteRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserService extends CommonMethods {

    @Autowired
    private WebsiteService websiteService;
    
    public Advertiser createAdvertiser(CreateAdvertiserRequest createAdvertiserRequest) throws GeneralExceptionHandler {

        Optional <Users> user = usersRepository.findById(createAdvertiserRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Advertiser advertiser = new Advertiser();

        if(createAdvertiserRequest.getAdvertId() != null){
            Optional<Advert> advert;
            ArrayList<Long> adverts = createAdvertiserRequest.getAdvertId();

            for (int i = 0; i < adverts.size(); i++) {
                advert = advertRepository.findById(adverts.get(i));

                if (!advert.isPresent()) {
                    throw new GeneralExceptionHandler(this.ADVERT_ERROR);
                }

                advertiser.getAdvertsAdvertiser().add(advert.get());
            }
        }

        if(createAdvertiserRequest.getReferredBy() != null){
            Optional <Users> referredBy = usersRepository.findById(createAdvertiserRequest.getReferredBy()); 
            if(!referredBy.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            advertiser.setReferredBy(referredBy.get());
        }

        advertiser.createAdvertiser(createAdvertiserRequest);
        advertiser.setUserId(user.get());
        advertiser.setCreatedBy(user.get());
        advertiser.setUpdatedBy(user.get());
        advertiserRepository.save(advertiser);

        if(createAdvertiserRequest.getWebsiteName() != null && createAdvertiserRequest.getWebsiteUrl() != null && createAdvertiserRequest.getWebsiteStatus() != null){
            CreateWebsiteRequest createWebsiteRequest = new CreateWebsiteRequest();
            createWebsiteRequest.setAdvertiser(advertiser.getId());
            createWebsiteRequest.setName(createAdvertiserRequest.getWebsiteName());
            createWebsiteRequest.setUrl(createAdvertiserRequest.getWebsiteUrl());
            createWebsiteRequest.setUserId(user.get().getId());
            createWebsiteRequest.setWebsiteStatus(createAdvertiserRequest.getWebsiteStatus());
            websiteService.createWebsite(createWebsiteRequest);
        }
        
        return advertiser;
    }

    public Advertiser updateParticularAdvertiser(UpdateParticularAdvertiserRequest updateParticularAdvertiserRequest) throws GeneralExceptionHandler {


        Optional <Advertiser> find = advertiserRepository.findById(updateParticularAdvertiserRequest.getAdvertiserId());

        if(!find.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Optional <Users> user = usersRepository.findById(updateParticularAdvertiserRequest.getUserId());

        if(!user.isPresent()){
            throw new GeneralExceptionHandler("User does not exit");
        }

        Advertiser advertiser = find.get();

        advertiser.updateAdvertiser(updateParticularAdvertiserRequest);

        if(updateParticularAdvertiserRequest.getUserId() != null){
            advertiser.setUserId(user.get());
        }

        if(updateParticularAdvertiserRequest.getReferredBy() != null){
            Optional <Users> referredBy = usersRepository.findById(updateParticularAdvertiserRequest.getReferredBy()); 
            if(!referredBy.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            advertiser.setReferredBy(referredBy.get());
        }

        if(updateParticularAdvertiserRequest.getUserId() != null){
            advertiser.setUpdatedBy(user.get());
        }

        if(updateParticularAdvertiserRequest.getWebsiteName() != null && updateParticularAdvertiserRequest.getWebsiteId() != null){
            UpdateWebsiteRequest update = new UpdateWebsiteRequest();
            update.setAdvertiser(advertiser.getId());
            update.setName(updateParticularAdvertiserRequest.getWebsiteName());
            update.setUserId(updateParticularAdvertiserRequest.getUserId());
            update.setWebsiteId(updateParticularAdvertiserRequest.getWebsiteId());
            websiteService.updateWebsite(update);

        }

        if(updateParticularAdvertiserRequest.getWebsiteUrl() != null && updateParticularAdvertiserRequest.getWebsiteId() != null){
            UpdateWebsiteRequest update = new UpdateWebsiteRequest();
            update.setAdvertiser(advertiser.getId());
            update.setUrl(updateParticularAdvertiserRequest.getWebsiteUrl());
            update.setUserId(updateParticularAdvertiserRequest.getUserId());
            update.setWebsiteId(updateParticularAdvertiserRequest.getWebsiteId());
            websiteService.updateWebsite(update);
        }

        if(updateParticularAdvertiserRequest.getWebsiteStatus() != null && updateParticularAdvertiserRequest.getWebsiteId() != null){
            UpdateWebsiteRequest update = new UpdateWebsiteRequest();
            update.setAdvertiser(advertiser.getId());
            update.setWebsiteStatus(updateParticularAdvertiserRequest.getWebsiteStatus());
            update.setUserId(updateParticularAdvertiserRequest.getUserId());
            update.setWebsiteId(updateParticularAdvertiserRequest.getWebsiteId());
            websiteService.updateWebsite(update);
        }

        advertiserRepository.save(advertiser);
        
        return advertiser;
    }

    public List <Advertiser> getAllAdvertisers(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Advertiser> advertisers = advertiserRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return advertisers.getContent();
    }

    public void deleteAdvertiser(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {

        Optional <Advertiser> find = advertiserRepository.findById(generalDeleteRequest.getId());

        if(!find.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Advertiser advertiser = find.get();
        advertiser.setIsDeleted(true);
        advertiser.setDeletedAt(LocalDateTime.now());
        advertiserRepository.save(advertiser);
    }

    public Advertiser getParticularAdvertiser(GetParticularAdvertiserRequest getParticularAdvertiserRequest) throws GeneralExceptionHandler {

        Optional <Advertiser> advertiser = advertiserRepository.findById(getParticularAdvertiserRequest.getAdvertiserId());

        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        return advertiser.get();
    }
}