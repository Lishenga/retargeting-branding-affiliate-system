package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.AdvertBehavior;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.advertbehavior.CreateAdvertBehaviorRequest;
import com.retargeting_branding.requests.advertbehavior.GetAdvertBehaviorForAdvertRequest;
import com.retargeting_branding.requests.advertbehavior.UpdateAdvertBehaviorRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertBehaviorService extends CommonMethods {

    public AdvertBehavior createAdvertBehavior(CreateAdvertBehaviorRequest createAdvertBehaviorRequest) throws GeneralExceptionHandler{
        
        Optional<Advert> ad = advertRepository.findById(createAdvertBehaviorRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional<Users> user = usersRepository.findById(createAdvertBehaviorRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }
        
        AdvertBehavior adBehave = new AdvertBehavior();
        adBehave.createAdvertBehavior(createAdvertBehaviorRequest);
        adBehave.setAdvert(ad.get());
        adBehave.setCreatedBy(user.get());
        advertBehaviorRepository.save(adBehave);

        return adBehave;
    }

    public List<AdvertBehavior> getAllAdvertBehaviors(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AdvertBehavior> adBehave = advertBehaviorRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return adBehave.getContent();
    }

    public void deleteAdvertBehavior(GeneralDeleteRequest generalDeleteRequest)throws GeneralExceptionHandler{

        Optional <AdvertBehavior> adBehave = advertBehaviorRepository.findById(generalDeleteRequest.getId());
        if(!adBehave.isPresent()){
            throw new GeneralExceptionHandler(this.advertBehaviorError);
        }

        AdvertBehavior adBeh = adBehave.get();
        adBeh.setIsDeleted(true);
        adBeh.setDeletedAt(LocalDateTime.now());
        advertBehaviorRepository.save(adBeh);
    }

    public AdvertBehavior getAdvertBehavior(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertBehavior> adBehave = advertBehaviorRepository.findById(generalParticularRequest.getId());
        if(!adBehave.isPresent()){
            throw new GeneralExceptionHandler(this.advertBehaviorError);
        }

        return adBehave.get();
    }

    public AdvertBehavior updateAdvertBehavior(UpdateAdvertBehaviorRequest updateAdvertBehaviorRequest) throws GeneralExceptionHandler{

        Optional <AdvertBehavior> adBehave = advertBehaviorRepository.findById(updateAdvertBehaviorRequest.getAdvertBehaviorId());
        if(!adBehave.isPresent()){
            throw new GeneralExceptionHandler(this.advertBehaviorError);
        }

        Optional <Users> user = usersRepository.findById(updateAdvertBehaviorRequest.getUpdatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        AdvertBehavior ad = adBehave.get();
        
        if(updateAdvertBehaviorRequest.getAdvertId() != null){
            Optional <Advert> advert = advertRepository.findById(updateAdvertBehaviorRequest.getAdvertId());
            if(!advert.isPresent()){
                throw new GeneralExceptionHandler(this.ADVERT_ERROR);
            }
            ad.setAdvert(advert.get());
        }
        ad.updateAdvertBehavior(updateAdvertBehaviorRequest);
        ad.setUpdatedBy(user.get());
        advertBehaviorRepository.save(ad);

        return ad;
    }

    public AdvertBehavior getAdvertBehaviorForAdvert(GetAdvertBehaviorForAdvertRequest getAdvertBehaviorForAdvertRequest) throws GeneralExceptionHandler{
        
        Optional <Advert> ad = advertRepository.findById(getAdvertBehaviorForAdvertRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional <AdvertBehavior> adBehave = advertBehaviorRepository.findByAdvert(ad.get());
        if(!adBehave.isPresent()){
            throw new GeneralExceptionHandler(this.advertBehaviorError);
        }

        return adBehave.get();
    }
}