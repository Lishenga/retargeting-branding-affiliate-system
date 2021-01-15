package com.retargeting_branding.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.enums.Generation;
import com.retargeting_branding.enums.TagsStatus;
import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.Impression;
import com.retargeting_branding.models.Website;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.impression.CreateImpressionRequest;
import com.retargeting_branding.requests.impression.GetAllImpressionsForAdvertiserRequest;
import com.retargeting_branding.requests.impression.GetAllImpressionsForWebsiteRequest;
import com.retargeting_branding.requests.tags.CreateTagRequest;
import com.retargeting_branding.responses.impression.GeneralImpressionResponse;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImpressionService extends CommonMethods {

    @Autowired
    private RedisTemplate<String, GeneralImpressionResponse> redisCreateImpressionResponse;

    @Autowired
    private TagsService tagsService;

    private GeneralImpressionResponse generalImpressionResponse = new GeneralImpressionResponse();

    private String redisCreateImpression = "createImpressionResponse";

    public Impression createImpression(CreateImpressionRequest createImpressionRequest)
            throws GeneralExceptionHandler, MalformedURLException {

        Optional<Website> website = websiteRepository.findById(createImpressionRequest.getWebsiteId());
        if (!website.isPresent()) {
            generalImpressionResponse.setData(null);
            generalImpressionResponse.setStatus(500);
            generalImpressionResponse.setMessage(this.websiteError);
            redisCreateImpressionResponse.convertAndSend(redisCreateImpression, generalImpressionResponse);

            throw new GeneralExceptionHandler(this.websiteError);
        }

        Impression impress = new Impression();
        impress.createImpression(createImpressionRequest);

        if (createImpressionRequest.getAdvertiserId() != null){
            Optional<Advertiser> advertiser = advertiserRepository.findById(createImpressionRequest.getAdvertiserId());
            if (!advertiser.isPresent()) {
                generalImpressionResponse.setData(null);
                generalImpressionResponse.setStatus(500);
                generalImpressionResponse.setMessage(this.ADVERTISER_ERROR);
                redisCreateImpressionResponse.convertAndSend(redisCreateImpression, generalImpressionResponse);

                throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
            }

            impress.setAdvertiser(advertiser.get());
            impress.setCreatedBy(advertiser.get());
            
        }

        if (createImpressionRequest.getUrl() != null)
            impress.setUrl(createImpressionRequest.getUrl());

        impress.setWebsite(website.get());

        impressionRepository.save(impress);

        if (createImpressionRequest.getUrl() != null){
            Optional<Advertiser> advertiser = advertiserRepository.findById(createImpressionRequest.getAdvertiserId());

            URL url = new URL(createImpressionRequest.getUrl());
            String[] urlPath = url.getPath().split("/"); 

            for (String a : urlPath){
                String[] tag = a.split("-"); 
                for(String b : tag){
                    CreateTagRequest createTagRequest = new CreateTagRequest();
                    createTagRequest.setCreatedBy(advertiser.get().getCreatedBy().getId());
                    createTagRequest.setGenerationType(Generation.AUTO);
                    createTagRequest.setName(b);
                    createTagRequest.setTagStatus(TagsStatus.ACTIVE);
                    createTagRequest.setDescription(b);
                    createTagRequest.setImpressionId(impress.getId());
                    
                    tagsService.createTag(createTagRequest);
                }
            }
        }

        generalImpressionResponse.setData(impress);
        generalImpressionResponse.setStatus(200);
        generalImpressionResponse.setMessage(this.SUCCESS);
        redisCreateImpressionResponse.convertAndSend(redisCreateImpression, generalImpressionResponse);

        return impress;
    }

    public List<Impression> getAllImpressions(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Impression> impress = impressionRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return impress.getContent();
    }

    public void deleteImpression(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <Impression> impress = impressionRepository.findById(generalDeleteRequest.getId());
        if(!impress.isPresent()){
            throw new GeneralExceptionHandler(this.impressionError);
        }

        Impression impression = impress.get();
        impression.setIsDeleted(true);
        impression.setDeletedAt(LocalDateTime.now());
        impressionRepository.save(impression);
    }

    public Impression getImpression(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <Impression> impress = impressionRepository.findById(generalParticularRequest.getId());
        if(!impress.isPresent()){
            throw new GeneralExceptionHandler(this.impressionError);
        }

        return impress.get();
    }

    public List<Impression> getAllImpressionsForAdvertiser(GetAllImpressionsForAdvertiserRequest getAllImpressionsForAdvertiserRequest) throws GeneralExceptionHandler{

        Optional <Advertiser> advertiser = advertiserRepository.findById(getAllImpressionsForAdvertiserRequest.getAdvertiserId());
        if(!advertiser.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Pageable find = PageRequest.of(getAllImpressionsForAdvertiserRequest.getPage(), getAllImpressionsForAdvertiserRequest.getItems());
        Page <Impression> impress = impressionRepository.findByAdvertiser(advertiser.get(), find);

        return impress.getContent();
    }

    public List<Impression> getAllImpressionsForWebsite(GetAllImpressionsForWebsiteRequest getAllImpressionsForWebsiteRequest) throws GeneralExceptionHandler{

        Optional <Website> website = websiteRepository.findById(getAllImpressionsForWebsiteRequest.getWebsiteId());
        if(!website.isPresent()){
            throw new GeneralExceptionHandler(this.websiteError);
        }

        Pageable find = PageRequest.of(getAllImpressionsForWebsiteRequest.getPage(), getAllImpressionsForWebsiteRequest.getItems());
        Page <Impression> impress = impressionRepository.findByWebsite(website.get(), find);

        return impress.getContent();
    }
}