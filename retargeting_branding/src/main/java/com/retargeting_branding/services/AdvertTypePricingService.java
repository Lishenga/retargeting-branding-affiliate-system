package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertTypePricing;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.adverttypepricing.CreateAdvertTypePricingRequest;
import com.retargeting_branding.requests.adverttypepricing.GetAllAdvertTypePricingByPricingModeRequest;
import com.retargeting_branding.requests.adverttypepricing.UpdateAdvertTypePricingRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertTypePricingService extends CommonMethods {
    
    public AdvertTypePricing createAdvertTypePricing(CreateAdvertTypePricingRequest createAdvertTypePricingRequest) throws GeneralExceptionHandler{

        Optional <Users> user = usersRepository.findById(createAdvertTypePricingRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        AdvertTypePricing adTypePricing = new AdvertTypePricing();
        adTypePricing.createAdvertTypePricing(createAdvertTypePricingRequest);
        adTypePricing.setCreatedBy(user.get());
        advertTypePricingRepository.save(adTypePricing);

        return adTypePricing;
    }

    public List<AdvertTypePricing> getAllAdvertTypePricings(GeneralPagedRequest generalPagedRequest){

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AdvertTypePricing> adTypePricing = advertTypePricingRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return adTypePricing.getContent();
    }

    public void deleteAdvertTypePricing(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypePricing> adTypePricing = advertTypePricingRepository.findById(generalDeleteRequest.getId());
        if(!adTypePricing.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypePricingError);
        }

        AdvertTypePricing ad = adTypePricing.get();
        ad.setDeletedAt(LocalDateTime.now());
        ad.setIsDeleted(true);
        advertTypePricingRepository.save(ad);
    }

    public AdvertTypePricing updateAdvertTypePricing(UpdateAdvertTypePricingRequest updateAdvertTypePricingRequest) throws GeneralExceptionHandler{

        Optional <Users> user = usersRepository.findById(updateAdvertTypePricingRequest.getUpdatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Optional <AdvertTypePricing> adTypePricing = advertTypePricingRepository.findById(updateAdvertTypePricingRequest.getAdvertTypePricingId());
        if(!adTypePricing.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypePricingError);
        }

        AdvertTypePricing ad = adTypePricing.get();
        ad.updateAdvertTypePricing(updateAdvertTypePricingRequest);
        ad.setUpdatedBy(user.get());
        advertTypePricingRepository.save(ad);

        return ad;
    }

    public AdvertTypePricing getAdvertTypePricing(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypePricing> adTypePricing = advertTypePricingRepository.findById(generalParticularRequest.getId());
        if(!adTypePricing.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypePricingError);
        }

        return adTypePricing.get();
    }

    public List<AdvertTypePricing>getAllAdvertTypePricingByPricingMode(GetAllAdvertTypePricingByPricingModeRequest getAllAdvertTypePricingByPricingModeRequest){

        Pageable find = PageRequest.of(getAllAdvertTypePricingByPricingModeRequest.getPage(), getAllAdvertTypePricingByPricingModeRequest.getItems());
        Page <AdvertTypePricing> adTypePricing = advertTypePricingRepository.findByPricingMode(getAllAdvertTypePricingByPricingModeRequest.getPricingMode(), find);

        return adTypePricing.getContent();
    }
}