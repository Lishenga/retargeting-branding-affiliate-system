package com.retargeting_branding.utils;

import com.retargeting_branding.dao.QueryDao;
import com.retargeting_branding.repositories.AdEventsRepository;
import com.retargeting_branding.repositories.AdvertBehaviorRepository;
import com.retargeting_branding.repositories.AdvertTypeCommissionRateRepository;
import com.retargeting_branding.repositories.AdvertRepository;
import com.retargeting_branding.repositories.AdvertTypePricingRepository;
import com.retargeting_branding.repositories.AdvertTypeRepository;
import com.retargeting_branding.repositories.AdvertiserRepository;
import com.retargeting_branding.repositories.AdvertsAdvertiserRepository;
import com.retargeting_branding.repositories.CompanyProfileRepository;
import com.retargeting_branding.repositories.CompanyRepository;
import com.retargeting_branding.repositories.ImpressionRepository;
import com.retargeting_branding.repositories.InvoiceRepository;
import com.retargeting_branding.repositories.NotificationRepository;
import com.retargeting_branding.repositories.PaymentsRepository;
import com.retargeting_branding.repositories.PromoCodesRepository;
import com.retargeting_branding.repositories.SocialInteractionsRepository;
import com.retargeting_branding.repositories.TagsRepository;
import com.retargeting_branding.repositories.UsersRepository;
import com.retargeting_branding.repositories.WebsiteRepository;
import com.retargeting_branding.responses.GeneralResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class CommonMethods {

    @Autowired
    protected UsersRepository usersRepository;

    @Autowired
    protected AdvertBehaviorRepository advertBehaviorRepository;

    @Autowired
    protected AdvertRepository advertRepository;

    @Autowired
    protected AdvertiserRepository advertiserRepository;

    @Autowired
    protected AdvertTypeRepository advertTypeRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CompanyProfileRepository companyProfileRepository;
    
    @Autowired
    protected InvoiceRepository invoiceRepository;

    @Autowired
    protected NotificationRepository notificationRepository;

    @Autowired
    protected WebsiteRepository websiteRepository;

    @Autowired
    protected TagsRepository tagsRepository;

    @Autowired
    protected ImpressionRepository impressionRepository;

    @Autowired
    protected PaymentsRepository paymentsRepository;

    @Autowired
    protected AdvertTypePricingRepository advertTypePricingRepository;

    @Autowired
    protected AdEventsRepository adEventsRepository;

    @Autowired
    protected AdvertTypeCommissionRateRepository advertTypeCommissionRateRepository;

    @Autowired
    protected PromoCodesRepository promoCodesRepository;

    @Autowired
    protected SocialInteractionsRepository socialInteractionsRepository;

    @Autowired
    protected AdvertsAdvertiserRepository advertsAdvertiserRepository;

    @Autowired
    protected QueryDao dao;

    @Autowired
    protected PromoCodesUtil promoCodesUtil;

    public CommonMethods(){
        //constructor
    }
    
    protected String ADVERT_TYPE_ERROR = "AdvertType not found";

    protected String USER_ERROR = "User not found";

    protected String ADVERTISER_ERROR = "Advertiser not found";

    protected String ADVERT_ERROR = "Advert not found";

    protected String WEBSITE_ERROR = "Website not found";

    protected String COMPANY_ERROR = "Company not found";
    
    protected String INVOICE_ERROR = "Invoice not found";

    protected String companyProfileError = "CompanyProfile not found";

    protected String notificationError = "Notification does not exit";

    protected String advertBehaviorError = "AdvertBehavior does not exit";

    protected String tagsError = "Tag does not exit";

    protected String websiteError = "Website not found";

    protected String impressionError = "Impression not found";

    protected String paymentError = "Payment not found";

    protected String advertTypePricingError = "AdvertTypePricing not found";

    protected String advertTypeCommissionRateError = "AdvertTypeCommissionRate not found";

    protected String adeventsError = "AdEvents not found";

    protected String SUCCESS = "success";

    protected GeneralResponse generalResponse = new GeneralResponse();
}