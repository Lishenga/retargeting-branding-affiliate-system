package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.AdvertType;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.Invoice;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.invoice.CreateInvoiceRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByInvoiceStatusForAdvertRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByInvoiceStatusForCompanyRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByInvoiceStatusRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByPaymentModeForAdvertRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByPaymentModeForCompanyRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesByPaymentModeRequest;
import com.retargeting_branding.requests.invoice.GetAllInvoicesForCompanyRequest;
import com.retargeting_branding.requests.invoice.UpdateInvoiceRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends CommonMethods{

    public Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest) throws GeneralExceptionHandler{
        
        Optional <AdvertType> advertType = advertTypeRepository.findById(createInvoiceRequest.getAdvertTypeId());
        if(!advertType.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
        }

        Optional <Users> user = usersRepository.findById(createInvoiceRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Optional <Advert> ad = advertRepository.findById(createInvoiceRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional <Company> company = companyRepository.findById(createInvoiceRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Invoice invoice = new Invoice();
        invoice.createInvoice(createInvoiceRequest, user.get(), advertType.get(), ad.get(), company.get());
        invoiceRepository.save(invoice);

        return invoice;
    }

    public List <Invoice> getAllInvoices(GeneralPagedRequest generalPagedRequest){
        
        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return invoice.getContent();
    }

    public List<Invoice> getAllInvoicesForCompany(GetAllInvoicesForCompanyRequest getAllInvoicesForCompanyRequest) throws GeneralExceptionHandler{

        Optional <Company> company = companyRepository.findById(getAllInvoicesForCompanyRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Pageable find = PageRequest.of(getAllInvoicesForCompanyRequest.getPage(), getAllInvoicesForCompanyRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByCompany(company.get(), find);

        return invoice.getContent();
    }

    public void deleteInvoice(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <Invoice> invoice = invoiceRepository.findById(generalDeleteRequest.getId());
        if(!invoice.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        Invoice inv = invoice.get();
        inv.setIsDeleted(true);
        inv.setDeletedAt(LocalDateTime.now());
        invoiceRepository.save(inv);
    }

    public Invoice updateInvoice(UpdateInvoiceRequest updateInvoiceRequest) throws GeneralExceptionHandler{

        Optional <Invoice> invoice = invoiceRepository.findById(updateInvoiceRequest.getInvoiceId());
        if(!invoice.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        Invoice inv = invoice.get();
        inv.updateInvoice(updateInvoiceRequest);

        if(updateInvoiceRequest.getAdvertId() != null){
            Optional <Advert> ad = advertRepository.findById(updateInvoiceRequest.getAdvertId());
            if(!ad.isPresent()){
                throw new GeneralExceptionHandler(this.ADVERT_ERROR);
            }
            inv.setAdvert(ad.get());
        }

        if(updateInvoiceRequest.getAdvertTypeId() != null){
            Optional <AdvertType> ad = advertTypeRepository.findById(updateInvoiceRequest.getAdvertTypeId());
            if(!ad.isPresent()){
                throw new GeneralExceptionHandler(this.ADVERT_TYPE_ERROR);
            }
            inv.setAdvertType(ad.get());
        }

        if(updateInvoiceRequest.getCompanyId() != null){
            Optional <Company> company = companyRepository.findById(updateInvoiceRequest.getCompanyId());
            if(!company.isPresent()){
                throw new GeneralExceptionHandler(this.COMPANY_ERROR);
            }
            inv.setCompany(company.get());
        }

        if(updateInvoiceRequest.getUpdatedBy() != null){
            Optional <Users> user = usersRepository.findById(updateInvoiceRequest.getUpdatedBy());
            if(!user.isPresent()){
                throw new GeneralExceptionHandler(this.USER_ERROR);
            }
            inv.setUpdatedBy(user.get());
        }

        invoiceRepository.save(inv);

        return inv;
    }

    public Invoice getInvoice(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <Invoice> invoice = invoiceRepository.findById(generalParticularRequest.getId());
        if(!invoice.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        return invoice.get();
    }

    public List <Invoice> getAllInvoicesByInvoiceStatus(GetAllInvoicesByInvoiceStatusRequest getAllInvoicesByPaymentStatusRequest){

        Pageable find = PageRequest.of(getAllInvoicesByPaymentStatusRequest.getPage(), getAllInvoicesByPaymentStatusRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByInvoiceStatus(getAllInvoicesByPaymentStatusRequest.getInvoiceStatus(), find);

        return invoice.getContent();
    }

    public List <Invoice> getAllInvoicesByInvoiceStatusForAdvert(GetAllInvoicesByInvoiceStatusForAdvertRequest getAllInvoicesByInvoiceStatusForAdvertRequest) throws GeneralExceptionHandler{
        
        Optional <Advert> ad = advertRepository.findById(getAllInvoicesByInvoiceStatusForAdvertRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Pageable find = PageRequest.of(getAllInvoicesByInvoiceStatusForAdvertRequest.getPage(), getAllInvoicesByInvoiceStatusForAdvertRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByAdvertAndInvoiceStatus(ad.get(), getAllInvoicesByInvoiceStatusForAdvertRequest.getInvoiceStatus(), find);

        return invoice.getContent();
    }

    public List <Invoice> getAllInvoicesByInvoiceStatusForCompany(GetAllInvoicesByInvoiceStatusForCompanyRequest getAllInvoicesByInvoiceStatusForCompanyRequest) throws GeneralExceptionHandler{
        
        Optional <Company> company = companyRepository.findById(getAllInvoicesByInvoiceStatusForCompanyRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Pageable find = PageRequest.of(getAllInvoicesByInvoiceStatusForCompanyRequest.getPage(), getAllInvoicesByInvoiceStatusForCompanyRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByCompanyAndInvoiceStatus(company.get(), getAllInvoicesByInvoiceStatusForCompanyRequest.getInvoiceStatus(), find);

        return invoice.getContent();
    }

    public List<Invoice> getAllInvoicesByPaymentMode(GetAllInvoicesByPaymentModeRequest getAllInvoicesByPaymentModeRequest){

        Pageable find = PageRequest.of(getAllInvoicesByPaymentModeRequest.getPage(), getAllInvoicesByPaymentModeRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByPaymentMode(getAllInvoicesByPaymentModeRequest.getPaymentMode(), find);

        return invoice.getContent();
    }

    public List<Invoice> getAllInvoicesByPaymentModeForAdvert(GetAllInvoicesByPaymentModeForAdvertRequest getAllInvoicesByPaymentModeForAdvertRequest) throws GeneralExceptionHandler{

        Optional <Advert> ad = advertRepository.findById(getAllInvoicesByPaymentModeForAdvertRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Pageable find = PageRequest.of(getAllInvoicesByPaymentModeForAdvertRequest.getPage(), getAllInvoicesByPaymentModeForAdvertRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByAdvertAndPaymentMode(ad.get(), getAllInvoicesByPaymentModeForAdvertRequest.getPaymentMode(), find);

        return invoice.getContent();
    }

    public List <Invoice> getAllInvoicesByPaymentModeForCompany(GetAllInvoicesByPaymentModeForCompanyRequest getAllInvoicesByPaymentModeForCompanyRequest) throws GeneralExceptionHandler{
        
        Optional <Company> company = companyRepository.findById(getAllInvoicesByPaymentModeForCompanyRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Pageable find = PageRequest.of(getAllInvoicesByPaymentModeForCompanyRequest.getPage(), getAllInvoicesByPaymentModeForCompanyRequest.getItems());
        Page <Invoice> invoice = invoiceRepository.findByCompanyAndPaymentMode(company.get(), getAllInvoicesByPaymentModeForCompanyRequest.getPaymentMode(), find);

        return invoice.getContent();
    }
}