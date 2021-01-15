package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Impression;
import com.retargeting_branding.models.Tags;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.tags.CreateTagRequest;
import com.retargeting_branding.requests.tags.GetAllTagsByGenerationTypeRequest;
import com.retargeting_branding.requests.tags.GetAllTagsByStatusRequest;
import com.retargeting_branding.requests.tags.GetAllTagsForAdvertRequest;
import com.retargeting_branding.requests.tags.GetAllTagsForImpressionRequest;
import com.retargeting_branding.requests.tags.UpdateTagRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagsService extends CommonMethods{

    public Tags createTag(CreateTagRequest createTagRequest) throws GeneralExceptionHandler{
        
        Optional <Users> user = usersRepository.findById(createTagRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Tags tag = new Tags();
        tag.createTag(createTagRequest);
        tag.setCreatedBy(user.get());

        if(createTagRequest.getAdvertId() != null){
            Optional <Advert> ad = advertRepository.findById(createTagRequest.getAdvertId());
            if(!ad.isPresent()){
                throw new GeneralExceptionHandler(this.ADVERT_ERROR);
            }
            tag.setAdvert(ad.get());
        }

        if(createTagRequest.getImpressionId() != null){
            Optional <Impression> impress = impressionRepository.findById(createTagRequest.getImpressionId());
            if(!impress.isPresent()){
                throw new GeneralExceptionHandler(this.impressionError);
            }
            tag.setImpression(impress.get());
        }

        tagsRepository.save(tag);

        return tag;
    }

    public List<Tags> getAllTags(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Tags> tag = tagsRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return tag.getContent();
    }

    public void deleteTag(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <Tags> tag = tagsRepository.findById(generalDeleteRequest.getId());
        if(!tag.isPresent()){
            throw new GeneralExceptionHandler(this.tagsError);
        }

        Tags tags = tag.get();
        tags.setIsDeleted(true);
        tags.setDeletedAt(LocalDateTime.now());
        tagsRepository.save(tags);
    }

    public Tags updateTag(UpdateTagRequest updateTagRequest) throws GeneralExceptionHandler{

        Optional <Tags> tags = tagsRepository.findById(updateTagRequest.getTagId());
        if(!tags.isPresent()){
            throw new GeneralExceptionHandler(this.tagsError);
        }

        Optional <Users> user = usersRepository.findById(updateTagRequest.getUpdatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Tags tag = tags.get();
        tag.updateTag(updateTagRequest);
        tag.setUpdatedBy(user.get());

        if(updateTagRequest.getAdvertId() != null){
            Optional <Advert> ad = advertRepository.findById(updateTagRequest.getAdvertId());
            if(!ad.isPresent()){
                throw new GeneralExceptionHandler(this.ADVERT_ERROR);
            }
            tag.setAdvert(ad.get());
        }

        if(updateTagRequest.getImpressionId() != null){
            Optional <Impression> impress = impressionRepository.findById(updateTagRequest.getImpressionId());
            if(!impress.isPresent()){
                throw new GeneralExceptionHandler(this.impressionError);
            }
            tag.setImpression(impress.get());
        }

        tagsRepository.save(tag);

        return tag;
    }

    public Tags getTag(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <Tags> tags = tagsRepository.findById(generalParticularRequest.getId());
        if(!tags.isPresent()){
            throw new GeneralExceptionHandler(this.tagsError);
        }

        return tags.get();
    }

    public List<Tags>getAllTagsbyStatus(GetAllTagsByStatusRequest getAllTagsbyStatusRequest){

        Pageable find = PageRequest.of(getAllTagsbyStatusRequest.getPage(), getAllTagsbyStatusRequest.getItems());
        Page <Tags> tag = tagsRepository.findByTagsStatus(getAllTagsbyStatusRequest.getTagsStatus(), find);

        return tag.getContent();
    }

    public List<Tags> getAllTagsbyGenerationType(GetAllTagsByGenerationTypeRequest getAllTagsByGenerationTypeRequest){

        Pageable find = PageRequest.of(getAllTagsByGenerationTypeRequest.getPage(), getAllTagsByGenerationTypeRequest.getItems());
        Page <Tags> tag = tagsRepository.findByGeneration(getAllTagsByGenerationTypeRequest.getGenerationType(), find);

        return tag.getContent();
    }

    public List<Tags> getAllTagsForAdvert(GetAllTagsForAdvertRequest getAllTagsForAdvertRequest) throws GeneralExceptionHandler {

        Optional <Advert> ad = advertRepository.findById(getAllTagsForAdvertRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Pageable find = PageRequest.of(getAllTagsForAdvertRequest.getPage(), getAllTagsForAdvertRequest.getItems());
        Page <Tags> tag = tagsRepository.findByAdvert(ad.get(), find);

        return tag.getContent();
    }

    public List<Tags>getAllTagsForImpression(GetAllTagsForImpressionRequest getAllTagsForImpressionRequest) throws GeneralExceptionHandler{

        Optional <Impression> impress = impressionRepository.findById(getAllTagsForImpressionRequest.getImpressionId());
        if(!impress.isPresent()){
            throw new GeneralExceptionHandler(this.impressionError);
        }

        Pageable find = PageRequest.of(getAllTagsForImpressionRequest.getPage(), getAllTagsForImpressionRequest.getItems());
        Page <Tags> tag = tagsRepository.findByImpression(impress.get(), find);

        return tag.getContent();
    }
}