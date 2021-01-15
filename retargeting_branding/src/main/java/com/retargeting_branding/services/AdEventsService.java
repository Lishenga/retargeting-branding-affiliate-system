package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdEvents;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Website;
import com.retargeting_branding.requests.adevents.CreateAdEventsRequest;
import com.retargeting_branding.requests.adevents.GetAllAdEventsForAdvertiserRequest;
import com.retargeting_branding.requests.adevents.GetAllAdEventsForWebsiteRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.responses.adevents.GeneralAdEventsResponse;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdEventsService extends CommonMethods{

    @Autowired
    private RedisTemplate<String, GeneralAdEventsResponse> redisCreateAdEventsResponse;

    private GeneralAdEventsResponse generalAdEventsResponse = new GeneralAdEventsResponse();

    private String redisCreateAdEvents = "createAdEventsResponse";
    
    public AdEvents createAdEvent(CreateAdEventsRequest createAdEventsRequest)throws GeneralExceptionHandler{

        Optional <Advert> ad = advertRepository.findById(createAdEventsRequest.getAdvertId());
        if(!ad.isPresent()){
            generalAdEventsResponse.setData(null);
            generalAdEventsResponse.setStatus(500);
            generalAdEventsResponse.setMessage(this.ADVERT_ERROR);
            redisCreateAdEventsResponse.convertAndSend(redisCreateAdEvents, generalAdEventsResponse);

            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional <Website> web = websiteRepository.findById(createAdEventsRequest.getWebsiteId());
        if(!web.isPresent()){
            generalAdEventsResponse.setData(null);
            generalAdEventsResponse.setStatus(500);
            generalAdEventsResponse.setMessage(this.websiteError);
            redisCreateAdEventsResponse.convertAndSend(redisCreateAdEvents, generalAdEventsResponse);

            throw new GeneralExceptionHandler(this.websiteError);
        }

        Optional <Advertiser> advertiser = advertiserRepository.findById(createAdEventsRequest.getAdvertiserId());
        if(!advertiser.isPresent()){
            generalAdEventsResponse.setData(null);
            generalAdEventsResponse.setStatus(500);
            generalAdEventsResponse.setMessage(this.ADVERTISER_ERROR);
            redisCreateAdEventsResponse.convertAndSend(redisCreateAdEvents, generalAdEventsResponse);

            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        AdEvents adevent = new AdEvents();
        adevent.createAdEvent(createAdEventsRequest);
        adevent.setAdvert(ad.get());
        adevent.setWebsite(web.get());
        adevent.setAdvertiser(advertiser.get());
        adevent.setCreatedBy(advertiser.get());
        adEventsRepository.save(adevent);

        generalAdEventsResponse.setData(adevent);
        generalAdEventsResponse.setStatus(200);
        generalAdEventsResponse.setMessage("Success");
        redisCreateAdEventsResponse.convertAndSend(redisCreateAdEvents, generalAdEventsResponse);

        return adevent;
    }

    public List<AdEvents> getAllAdEvents(GeneralPagedRequest generalPagedRequest){

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AdEvents> adevents = adEventsRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return adevents.getContent();
    }

    public void deleteAdEvent(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <AdEvents> adevent = adEventsRepository.findById(generalDeleteRequest.getId());
        if(!adevent.isPresent()){
            throw new GeneralExceptionHandler(this.adeventsError);
        }

        AdEvents ad = adevent.get();
        ad.setIsDeleted(true);
        ad.setDeletedAt(LocalDateTime.now());
        adEventsRepository.save(ad);
    }

    public AdEvents getAdEvent(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdEvents> ad = adEventsRepository.findById(generalParticularRequest.getId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.adeventsError);
        }

        return ad.get();
    }

    public List<AdEvents> getAllAdEventsForAdvertiser(GetAllAdEventsForAdvertiserRequest getAllAdEventsForAdvertiserRequest) throws GeneralExceptionHandler{

        Optional <Advertiser> advertiser = advertiserRepository.findById(getAllAdEventsForAdvertiserRequest.getAdvertiserId());
        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Pageable find = PageRequest.of(getAllAdEventsForAdvertiserRequest.getPage(), getAllAdEventsForAdvertiserRequest.getItems());
        Page <AdEvents> adevents = adEventsRepository.findByAdvertiser(advertiser.get(), find);

        return adevents.getContent();
    }

    public List<AdEvents> getAllAdEventsForWebsite(GetAllAdEventsForWebsiteRequest getAllAdEventsForWebsiteRequest) throws GeneralExceptionHandler{

        Optional <Website> website = websiteRepository.findById(getAllAdEventsForWebsiteRequest.getWebsiteId());
        if(!website.isPresent()){
            throw new GeneralExceptionHandler(this.websiteError);
        }

        Pageable find = PageRequest.of(getAllAdEventsForWebsiteRequest.getPage(), getAllAdEventsForWebsiteRequest.getItems());
        Page <AdEvents> adevents = adEventsRepository.findByWebsite(website.get(), find);

        return adevents.getContent();
    }
}