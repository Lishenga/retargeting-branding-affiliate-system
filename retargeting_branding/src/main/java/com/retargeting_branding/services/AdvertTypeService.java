package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertType;
import com.retargeting_branding.models.AdvertTypeCommissionRate;
import com.retargeting_branding.models.AdvertTypePricing;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.adverttype.CreateAdvertTypeRequest;
import com.retargeting_branding.requests.adverttype.UpdateAdvertTypeRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertTypeService extends CommonMethods {
    
    public AdvertType createAdvertType(CreateAdvertTypeRequest createAdvertTypeRequest) throws GeneralExceptionHandler{
        
        Optional<AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(createAdvertTypeRequest.getAdvertTypeCommissionRateId());
        if(!rate.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
        }

        Optional<AdvertTypePricing> pricing = advertTypePricingRepository.findById(createAdvertTypeRequest.getAdvertTypePricingId());
        if(!pricing.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypePricingError);
        }

        Optional<Users> user = usersRepository.findById(createAdvertTypeRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        AdvertType adtype = new AdvertType();
        adtype.createAdvertType(createAdvertTypeRequest);
        adtype.setAdvertTypeCommissionRate(rate.get());
        adtype.setAdvertTypePricing(pricing.get());
        adtype.setCreatedBy(user.get());
        advertTypeRepository.save(adtype);

        return adtype;
    }

    public List<AdvertType> getAllAdvertTypes(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AdvertType> adtype = advertTypeRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return adtype.getContent();
    }

    public void deleteAdvertType(GeneralDeleteRequest generalDeleteRequest)throws GeneralExceptionHandler{

        Optional<AdvertType> adtype = advertTypeRepository.findById(generalDeleteRequest.getId());
        if(!adtype.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        AdvertType ad = adtype.get();
        ad.setIsDeleted(true);
        ad.setDeletedAt(LocalDateTime.now());
        advertTypeRepository.save(ad);
    }

    public AdvertType updateAdvertType(UpdateAdvertTypeRequest updateAdvertTypeRequest) throws GeneralExceptionHandler{

        Optional<AdvertType> adtype = advertTypeRepository.findById(updateAdvertTypeRequest.getAdvertTypeId());
        if(!adtype.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        AdvertType adv = adtype.get();

        if(updateAdvertTypeRequest.getAdvertTypeCommissionRateId() != null){
            Optional<AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(updateAdvertTypeRequest.getAdvertTypeCommissionRateId());
            if(!rate.isPresent()){
                throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
            }
            adv.setAdvertTypeCommissionRate(rate.get());
        }

        if(updateAdvertTypeRequest.getAdvertTypePricingId() != null){
            Optional<AdvertTypePricing> pricing = advertTypePricingRepository.findById(updateAdvertTypeRequest.getAdvertTypePricingId());
            if(!pricing.isPresent()){
                throw new GeneralExceptionHandler(this.advertTypePricingError);
            }

            adv.setAdvertTypePricing(pricing.get());
        }

        if(updateAdvertTypeRequest.getUpdatedBy() != null){
            Optional<Users> user = usersRepository.findById(updateAdvertTypeRequest.getUpdatedBy());
            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }

            adv.setUpdatedBy(user.get());
        }

        adv.updateAdvertType(updateAdvertTypeRequest);
        advertTypeRepository.save(adv);

        return adv;
    }

    public AdvertType getAdvertType(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertType> adtype = advertTypeRepository.findById(generalParticularRequest.getId());
        if(!adtype.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        return adtype.get();
    }

    public AdvertType getAdvertTypeForAdvertTypeCommissionRate(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(generalParticularRequest.getId());
        if(!rate.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
        }

        Optional <AdvertType> adtype = advertTypeRepository.findByAdvertTypeCommissionRate(rate.get());
        if(!adtype.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        return adtype.get();
    }

    public AdvertType getAdvertTypeForAdvertTypePricing(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypePricing> pricing = advertTypePricingRepository.findById(generalParticularRequest.getId());
        if(!pricing.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypePricingError);
        }

        Optional <AdvertType> adtype = advertTypeRepository.findByAdvertTypePricing(pricing.get());
        if(!adtype.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        return adtype.get();
    }
}