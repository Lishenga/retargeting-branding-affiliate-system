package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.AdvertTypeCommissionRate;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.adverttypecommissionrate.CreateAdvertTypeCommissionRateRequest;
import com.retargeting_branding.requests.adverttypecommissionrate.UpdateAdvertTypeCommissionRateRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertTypeCommissionRateService extends CommonMethods {
    
    public AdvertTypeCommissionRate createAdvertTypeCommissionRate(CreateAdvertTypeCommissionRateRequest createAdvertTypeCommissionRateRequest) throws GeneralExceptionHandler{

        Optional <Users> user = usersRepository.findById(createAdvertTypeCommissionRateRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        AdvertTypeCommissionRate rate = new AdvertTypeCommissionRate();
        rate.createAdvertTypeCommissionRate(createAdvertTypeCommissionRateRequest);
        rate.setCreatedBy(user.get());
        advertTypeCommissionRateRepository.save(rate);

        return rate;
    }

    public List<AdvertTypeCommissionRate> getAllAdvertTypesCommissionRate(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return rate.getContent();
    }

    public void deleteAdvertTypeCommissionRate(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional<AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(generalDeleteRequest.getId());
        if(!rate.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
        }

        AdvertTypeCommissionRate rat = rate.get();
        rat.setIsDeleted(true);
        rat.setDeletedAt(LocalDateTime.now());
        advertTypeCommissionRateRepository.save(rat);
    }

    public AdvertTypeCommissionRate updateAdvertTypeCommissionRate(UpdateAdvertTypeCommissionRateRequest updateAdvertTypeCommissionRateRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(updateAdvertTypeCommissionRateRequest.getAdvertTypeCommissionRateId());
        if(!rate.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
        }

        Optional <Users> user = usersRepository.findById(updateAdvertTypeCommissionRateRequest.getUpdatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        AdvertTypeCommissionRate rat = rate.get();
        rat.updateAdvertTypeCommissionRate(updateAdvertTypeCommissionRateRequest);
        rat.setUpdatedBy(user.get());

        advertTypeCommissionRateRepository.save(rat);

        return rat;
    }

    public AdvertTypeCommissionRate getAdvertTypeCommissionRate(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <AdvertTypeCommissionRate> rate = advertTypeCommissionRateRepository.findById(generalParticularRequest.getId());
        if(!rate.isPresent()){
            throw new GeneralExceptionHandler(this.advertTypeCommissionRateError);
        }

        return rate.get();
    }
}