package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.enums.Identity;
import com.retargeting_branding.enums.PaymentStatus;
import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.AdvertType;
import com.retargeting_branding.models.Advertiser;
import com.retargeting_branding.models.AdvertsAdvertiser;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.PromoCodes;
import com.retargeting_branding.models.SocialInteractions;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.models.Website;
import com.retargeting_branding.requests.AdvertsAdvertiser.CreateAdvertsAdvertiserRequest;
import com.retargeting_branding.requests.advert.CreateAdvertRequest;
import com.retargeting_branding.requests.advert.DisplayAdvertsOnDeviceRequest;
import com.retargeting_branding.requests.advert.DisplayTagsOnDeviceRequest;
import com.retargeting_branding.requests.advert.GetAdvertRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusForAdvertiserRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusForCompanyRequest;
import com.retargeting_branding.requests.advert.GetAdvertsByStatusRequest;
import com.retargeting_branding.requests.advert.SocialOrAffiliateRequest;
import com.retargeting_branding.requests.advert.UpdateAdvertRequest;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.promocodes.CreatePromoCodeRequest;
import com.retargeting_branding.requests.socialinteractions.CreateSocialInteractionsRequest;
import com.retargeting_branding.requests.socialinteractions.UpdateSocialInteractionsRequest;
import com.retargeting_branding.utils.CommonMethods;
import com.retargeting_branding.utils.DisplayAdverts;
import com.retargeting_branding.utils.GetTags;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertService extends CommonMethods {

    @Value("${system-config.server.api}")
    private String serverApi;

    public Advert createAdvert(CreateAdvertRequest createAdvertRequest) throws GeneralExceptionHandler {
        ;

        Optional<AdvertType> type = advertTypeRepository.findById(createAdvertRequest.getAdvertTypeId());

        if (!type.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        Optional<Company> company = companyRepository.findById(createAdvertRequest.getCompanyId());

        if (!company.isPresent()) {
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional<Users> user = usersRepository.findById(createAdvertRequest.getCreatedBy());

        if (!user.isPresent()) {
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Advert ad = new Advert();

        if (createAdvertRequest.getAdvertiserId() != null) {
            Optional<Advertiser> advertiser;
            ArrayList<Long> advertisers = createAdvertRequest.getAdvertiserId();

            for (int i = 0; i < advertisers.size(); i++) {
                advertiser = advertiserRepository.findById(advertisers.get(i));

                if (!advertiser.isPresent()) {
                    throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
                }

                ad.getAdvertiser().add(advertiser.get());
            }
        }

        ad.createAdvert(createAdvertRequest, serverApi);
        ad.setAdvertType(type.get());
        // ad.setAdvertiser(advertiser.get());
        ad.setCompany(company.get());
        ad.setCreatedBy(user.get());
        ad.setUpdatedBy(user.get());

        advertRepository.save(ad);
        return ad;
    }

    public String redirect(String randomString) {
        String url = "http://lanthanion.com";
        if (randomString.contains("WEBSITE")) {
            Long last = Long.parseLong(randomString.substring(randomString.length() - 1));
            Optional<Website> web = websiteRepository.findById(last);
            if (!web.isPresent())
                return url;

            String redirectUrl = serverApi + "api/advert/redirect/" + randomString.substring(randomString.length() - 8);
            return advertRepository.findByRedirectLink(redirectUrl).getActualLink();

        } else if (randomString.contains("AFFLIATE")) {
            Long last = Long.parseLong(randomString.substring(randomString.length() - 1));
            Optional<Advertiser> advertiser = advertiserRepository.findById(last);
            if (!advertiser.isPresent()) {
                String urlPromoCode = randomString.substring(randomString.length() - 9);
                Optional<PromoCodes> code = promoCodesRepository.findByPromoCode(urlPromoCode);
                if (!code.isPresent()) {
                    return url;
                } else {
                    String promoCode = promoCodesUtil.generateToken(urlPromoCode);
                    CreatePromoCodeRequest createPromoCodeRequest = new CreatePromoCodeRequest();
                    createPromoCodeRequest.setAdvertiserId(advertiser.get().getId());
                    createPromoCodeRequest.setPromoCode(promoCode);

                    PromoCodes promoCodes = new PromoCodes();
                    promoCodes.createPromoCode(createPromoCodeRequest, null, advertiser.get().getCreatedBy());
                    promoCodesRepository.save(promoCodes);

                    String redirectUrl = serverApi + "api/advert/redirect/"
                            + randomString.substring(randomString.length() - 17);
                    return advertRepository.findByRedirectLink(redirectUrl).getActualLink() + "?promocode=" + promoCode;
                }

            }

            String promoCode = promoCodesUtil.generateToken(String.valueOf(advertiser.get().getId()));
            CreatePromoCodeRequest createPromoCodeRequest = new CreatePromoCodeRequest();
            createPromoCodeRequest.setAdvertiserId(advertiser.get().getId());
            createPromoCodeRequest.setCreatedBy(advertiser.get().getCreatedBy().getId());
            createPromoCodeRequest.setPromoCode(promoCode);

            PromoCodes promoCodes = new PromoCodes();
            promoCodes.createPromoCode(createPromoCodeRequest, advertiser.get(), advertiser.get().getCreatedBy());
            promoCodesRepository.save(promoCodes);

            String redirectUrl = serverApi + "api/advert/redirect/" + randomString.substring(randomString.length() - 9);
            return advertRepository.findByRedirectLink(redirectUrl).getActualLink() + "?promocode=" + promoCode;

        } else if (randomString.contains("SOCIAL")) {
            Long last = Long.parseLong(randomString.substring(randomString.length() - 1));
            Optional<Advertiser> advertiser = advertiserRepository.findById(last);
            if (!advertiser.isPresent())
                return url;
            String redirectUrl = serverApi + "api/advert/redirect/" + randomString.substring(randomString.length() - 7);

            Optional<SocialInteractions> social = socialInteractionsRepository
                    .findByAdvertAndAdvertiser(advertRepository.findByRedirectLink(redirectUrl), advertiser.get());
            if (!social.isPresent()) {
                CreateSocialInteractionsRequest createSocialInteractionsRequest = new CreateSocialInteractionsRequest();
                createSocialInteractionsRequest.setAdvertId(advertRepository.findByRedirectLink(redirectUrl).getId());
                createSocialInteractionsRequest.setAdvertiserId(advertiser.get().getId());
                createSocialInteractionsRequest.setCount(1L);
                createSocialInteractionsRequest.setCreatedBy(advertiser.get().getCreatedBy().getId());

                SocialInteractions socialInteractions = new SocialInteractions();
                socialInteractions.createSocialInteraction(createSocialInteractionsRequest,
                        advertRepository.findByRedirectLink(redirectUrl), advertiser.get(),
                        advertiser.get().getCreatedBy());
                socialInteractionsRepository.save(socialInteractions);

                return advertRepository.findByRedirectLink(redirectUrl).getActualLink();
            }

            UpdateSocialInteractionsRequest updateSocialInteractionsRequest = new UpdateSocialInteractionsRequest();
            updateSocialInteractionsRequest.setAdvertId(advertRepository.findByRedirectLink(redirectUrl).getId());
            updateSocialInteractionsRequest.setAdvertiserId(advertiser.get().getId());
            updateSocialInteractionsRequest.setCount(social.get().getCount() + 1L);
            updateSocialInteractionsRequest.setUpdatedBy(advertiser.get().getCreatedBy().getId());

            SocialInteractions socialInteractions = social.get();
            socialInteractions.updateSocialInteractions(updateSocialInteractionsRequest,
                    advertRepository.findByRedirectLink(redirectUrl), advertiser.get(),
                    advertiser.get().getCreatedBy());
            socialInteractionsRepository.save(socialInteractions);

            return advertRepository.findByRedirectLink(redirectUrl).getActualLink();
        }
        return url;
    }

    public List<Advert> getAllAdverts(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page<Advert> adverts = advertRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return adverts.getContent();
    }

    public void deleteAdvert(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {

        Optional<Advert> ad = advertRepository.findById(generalDeleteRequest.getId());

        if (!ad.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Advert add = ad.get();
        add.setIsDeleted(true);
        add.setDeletedAt(LocalDateTime.now());
        advertRepository.save(add);
    }

    public Advert updateAdvert(UpdateAdvertRequest updateAdvertRequest) throws GeneralExceptionHandler {

        Optional<Advert> advert = advertRepository.findById(updateAdvertRequest.getAdvertId());

        if (!advert.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Advert ad = advert.get();

        if (updateAdvertRequest.getAdvertTypeId() != null) {
            Optional<AdvertType> type = advertTypeRepository.findById(updateAdvertRequest.getAdvertTypeId());
            if (!type.isPresent()) {
                throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
            }
            ad.setAdvertType(type.get());
        }

        if (updateAdvertRequest.getCompanyId() != null) {
            Optional<Company> company = companyRepository.findById(updateAdvertRequest.getCompanyId());
            if (!company.isPresent()) {
                throw new GeneralExceptionHandler(this.COMPANY_ERROR);
            }
            ad.setCompany(company.get());
        }

        if (updateAdvertRequest.getAdvertiserId() != null) {
            Optional<Advertiser> advertiser = advertiserRepository.findById(updateAdvertRequest.getAdvertiserId());
            if (!advertiser.isPresent()) {
                throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
            }
            ad.getAdvertiser().add(advertiser.get());
        }

        Optional<Users> user = usersRepository.findById(updateAdvertRequest.getUpdatedBy());
        if (!user.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }
        ad.setUpdatedBy(user.get());
        ad.updateAdvert(updateAdvertRequest);
        advertRepository.save(ad);

        return ad;
    }

    public Advert getAdvert(GetAdvertRequest getAdvertRequest) throws GeneralExceptionHandler {

        Optional<Advert> ad = advertRepository.findById(getAdvertRequest.getAdvertId());
        if (!ad.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }
        return ad.get();
    }

    public List<Advert> getAdvertsByStatus(GetAdvertsByStatusRequest getAdvertsByStatusRequest) {

        Pageable find = PageRequest.of(getAdvertsByStatusRequest.getPage(), getAdvertsByStatusRequest.getItems());
        Page<Advert> adverts = advertRepository.findByAdvertStatus(getAdvertsByStatusRequest.getAdvertStatus(), find);

        return adverts.getContent();
    }

    public List<Advert> getAdvertsByStatusForAdvertiser(
            GetAdvertsByStatusForAdvertiserRequest getAdvertsByStatusForAdvertiserRequest)
            throws GeneralExceptionHandler {

        Optional<Advertiser> advertiser = advertiserRepository
                .findById(getAdvertsByStatusForAdvertiserRequest.getAdvertiserId());
        if (!advertiser.isPresent()) {
            throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
        }

        Pageable find = PageRequest.of(getAdvertsByStatusForAdvertiserRequest.getPage(),
                getAdvertsByStatusForAdvertiserRequest.getItems());
        Page<Advert> adverts = advertRepository.findByAdvertStatusAndAdvertiser(
                getAdvertsByStatusForAdvertiserRequest.getAdvertStatus(), advertiser.get(), find);

        return adverts.getContent();
    }

    public List<Advert> getAdvertsByStatusForCompany(
            GetAdvertsByStatusForCompanyRequest getAdvertsByStatusForCompanyRequest) throws GeneralExceptionHandler {

        Optional<Company> company = companyRepository.findById(getAdvertsByStatusForCompanyRequest.getCompanyId());
        if (!company.isPresent()) {
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Pageable find = PageRequest.of(getAdvertsByStatusForCompanyRequest.getPage(),
                getAdvertsByStatusForCompanyRequest.getItems());
        Page<Advert> adverts = advertRepository.findByAdvertStatusAndCompany(
                getAdvertsByStatusForCompanyRequest.getAdvertStatus(), company.get(), find);

        return adverts.getContent();
    }

    public List<DisplayAdverts> displayAdvertsOnDevice(DisplayAdvertsOnDeviceRequest displayAdvertsOnDeviceRequest)
            throws GeneralExceptionHandler {

        Optional<Website> web = websiteRepository.findById(displayAdvertsOnDeviceRequest.getWebsiteId());
        if (!web.isPresent())
            throw new GeneralExceptionHandler(this.websiteError);

        List<DisplayAdverts> dataAdverts = advertRepository.getDisplayAdverts(
                displayAdvertsOnDeviceRequest.getDeviceType(), displayAdvertsOnDeviceRequest.getCountry(), 20,
                Identity.WEBSITE, web.get().getId());
        List<GetTags> dataTags = tagsRepository.getTags(displayAdvertsOnDeviceRequest.getDeviceId());

        List<DisplayAdverts> displayAdvertsDevice = new ArrayList<DisplayAdverts>();
        Website website = web.get();

        for (int e = 0; e < dataTags.size(); e++) {
            for (int i = 0; i < dataAdverts.size(); i++) {
                if (dataAdverts.get(i).getName().equals(dataTags.get(e).getName())) {
                    Optional<Advert> advert = advertRepository.findById(dataAdverts.get(i).getId());
                    website.getAdvert().add(advert.get());
                    displayAdvertsDevice.add(dataAdverts.get(i));
                }
            }
        }

        websiteRepository.save(website);
        return displayAdvertsDevice;
    }

    public List<GetTags> displayTagsOnDevice(DisplayTagsOnDeviceRequest displayTagsOnDeviceRequest) {
        return tagsRepository.getTags(displayTagsOnDeviceRequest.getDeviceId());
    }

    public List<DisplayAdverts> getAdvertsForSocialOrAffiliate(SocialOrAffiliateRequest socialOrAffiliateRequest)
            throws GeneralExceptionHandler {

        if (socialOrAffiliateRequest.getIdentity() == Identity.AFFILIATE) {
            Optional<Advertiser> advertiser = advertiserRepository.findById(socialOrAffiliateRequest.getId());
            if (!advertiser.isPresent()) {
                throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
            }

            List<DisplayAdverts> dataAdverts = advertRepository.getDisplayAdverts("DESKTOP", advertiser.get().getCountry(),
            socialOrAffiliateRequest.getNumberOfAds(), Identity.AFFILIATE, advertiser.get().getId());

            for (int i = 0; i < dataAdverts.size(); i++) {
                CreateAdvertsAdvertiserRequest createAdvertsAdvertiserRequest = new CreateAdvertsAdvertiserRequest();
                createAdvertsAdvertiserRequest.setAdvertId(dataAdverts.get(i).getId());
                createAdvertsAdvertiserRequest.setAdvertiserId(advertiser.get().getId());
                createAdvertsAdvertiserRequest.setCreatedBy(advertiser.get().getId());
                createAdvertsAdvertiserRequest.setIdentity(Identity.AFFILIATE);
                createAdvertsAdvertiserRequest.setPaymentStatus(PaymentStatus.PENDING);

                Optional <Advert> advert = advertRepository.findById(dataAdverts.get(i).getId());

                AdvertsAdvertiser advertsAdvertiser = new AdvertsAdvertiser();
                advertsAdvertiser.createAdvertsAdvertiser(createAdvertsAdvertiserRequest, advertiser.get());
                advertsAdvertiser.setAdvert(advert.get());

                advertsAdvertiserRepository.save(advertsAdvertiser);
            }

            return advertRepository.getDisplayAdverts("DESKTOP", advertiser.get().getCountry(),
                    socialOrAffiliateRequest.getNumberOfAds(), Identity.AFFILIATE, advertiser.get().getId());
        } else if (socialOrAffiliateRequest.getIdentity() == Identity.SOCIAL) {
            Optional<Advertiser> advertiser = advertiserRepository.findById(socialOrAffiliateRequest.getId());
            if (!advertiser.isPresent()) {
                throw new GeneralExceptionHandler(this.ADVERTISER_ERROR);
            }

            List<DisplayAdverts> dataAdverts = advertRepository.getDisplayAdverts("DESKTOP", advertiser.get().getCountry(),
            socialOrAffiliateRequest.getNumberOfAds(), Identity.SOCIAL, advertiser.get().getId());

            for (int i = 0; i < dataAdverts.size(); i++) {
                CreateAdvertsAdvertiserRequest createAdvertsAdvertiserRequest = new CreateAdvertsAdvertiserRequest();
                createAdvertsAdvertiserRequest.setAdvertId(dataAdverts.get(i).getId());
                createAdvertsAdvertiserRequest.setAdvertiserId(advertiser.get().getId());
                createAdvertsAdvertiserRequest.setCreatedBy(advertiser.get().getId());
                createAdvertsAdvertiserRequest.setIdentity(Identity.SOCIAL);
                createAdvertsAdvertiserRequest.setPaymentStatus(PaymentStatus.PENDING);

                Optional <Advert> advert = advertRepository.findById(dataAdverts.get(i).getId());

                AdvertsAdvertiser advertsAdvertiser = new AdvertsAdvertiser();
                advertsAdvertiser.createAdvertsAdvertiser(createAdvertsAdvertiserRequest, advertiser.get());
                advertsAdvertiser.setAdvert(advert.get());

                advertsAdvertiserRepository.save(advertsAdvertiser);
            }

            return advertRepository.getDisplayAdverts("DESKTOP", advertiser.get().getCountry(),
                    socialOrAffiliateRequest.getNumberOfAds(), Identity.SOCIAL, advertiser.get().getId());
        }

        return new ArrayList<DisplayAdverts>();
    }
}