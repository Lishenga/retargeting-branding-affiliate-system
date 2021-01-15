package com.retargeting_branding.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Company;
import com.retargeting_branding.models.Invoice;
import com.retargeting_branding.models.Payments;
import com.retargeting_branding.models.Users;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.payments.CreatePaymentRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentByPaymentModeRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsByPaymentStatusRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsForAdvertRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsForCompanyRequest;
import com.retargeting_branding.requests.payments.UpdatePaymentRequest;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService extends CommonMethods {
    
    public Payments createPayment(CreatePaymentRequest createPaymentRequest) throws GeneralExceptionHandler{

        Optional <Company> company = companyRepository.findById(createPaymentRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional <Advert> ad = advertRepository.findById(createPaymentRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional <Invoice> inv = invoiceRepository.findById(createPaymentRequest.getInvoiceId());
        if(!inv.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        Optional <Users> user = usersRepository.findById(createPaymentRequest.getCreatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Payments payment = new Payments();
        payment.createPayment(createPaymentRequest);
        payment.setCompany(company.get());
        payment.setAdvert(ad.get());
        payment.setInvoice(inv.get());
        payment.setCreatedBy(user.get());
        paymentsRepository.save(payment);

        return payment;
    }

    public List<Payments> getAllPayments(GeneralPagedRequest generalPagedRequest){

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Payments> payments = paymentsRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return payments.getContent();
    }

    public void deletePayment(GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler{

        Optional <Payments> payment = paymentsRepository.findById(generalDeleteRequest.getId());
        if(!payment.isPresent()){
            throw new GeneralExceptionHandler(this.paymentError);
        }

        Payments pay = payment.get();
        pay.setIsDeleted(true);
        pay.setDeletedAt(LocalDateTime.now());
        paymentsRepository.save(pay);
    }

    public Payments updatePayment(UpdatePaymentRequest updatePaymentRequest)throws GeneralExceptionHandler{

        Optional <Payments> payment = paymentsRepository.findById(updatePaymentRequest.getPaymentId());
        if(!payment.isPresent()){
            throw new GeneralExceptionHandler(this.paymentError);
        }

        Optional <Advert> ad = advertRepository.findById(updatePaymentRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }

        Optional <Company> company = companyRepository.findById(updatePaymentRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }

        Optional <Invoice> inv = invoiceRepository.findById(updatePaymentRequest.getInvoiceId());
        if(!inv.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        Optional <Users> user = usersRepository.findById(updatePaymentRequest.getUpdatedBy());
        if(!user.isPresent()){
            throw new GeneralExceptionHandler(this.USER_ERROR);
        }

        Payments pay = payment.get();
        pay.updatePayment(updatePaymentRequest);
        pay.setCompany(company.get());
        pay.setAdvert(ad.get());
        pay.setUpdatedBy(user.get());
        pay.setInvoice(inv.get());
        paymentsRepository.save(pay);

        return pay;
    }

    public Payments getPayment(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional <Payments> pay = paymentsRepository.findById(generalParticularRequest.getId());
        if(!pay.isPresent()){
            throw new GeneralExceptionHandler(this.paymentError);
        }

        return pay.get();
    }

    public Payments getPaymentForInvoice(GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler{

        Optional<Invoice> inv = invoiceRepository.findById(generalParticularRequest.getId());
        if(!inv.isPresent()){
            throw new GeneralExceptionHandler(this.INVOICE_ERROR);
        }

        Optional<Payments> payment = paymentsRepository.findByInvoice(inv.get());
        if(!payment.isPresent()){
            throw new GeneralExceptionHandler(this.paymentError);
        }

        return payment.get();
    }

    public List<Payments> getAllPaymentByPaymentStatus(GetAllPaymentsByPaymentStatusRequest getAllPaymentsByPaymentStatusRequest){

        Pageable find = PageRequest.of(getAllPaymentsByPaymentStatusRequest.getPage(), getAllPaymentsByPaymentStatusRequest.getItems());
        Page <Payments> payments = paymentsRepository.findByPaymentStatus(getAllPaymentsByPaymentStatusRequest.getPaymentStatus(), find);

        return payments.getContent();
    }

    public List<Payments> getAllPaymentByPaymentMode(GetAllPaymentByPaymentModeRequest getAllPaymentByPaymentModeRequest){

        Pageable find = PageRequest.of(getAllPaymentByPaymentModeRequest.getPage(), getAllPaymentByPaymentModeRequest.getItems());
        Page <Payments> payments = paymentsRepository.findByPaymentMode(getAllPaymentByPaymentModeRequest.getPaymentMode(), find);

        return payments.getContent();
    }

    public List<Payments> getAllPaymentsForAdvert(GetAllPaymentsForAdvertRequest getAllPaymentsForAdvertRequest) throws GeneralExceptionHandler{
        
        Optional<Advert> ad = advertRepository.findById(getAllPaymentsForAdvertRequest.getAdvertId());
        if(!ad.isPresent()){
            throw new GeneralExceptionHandler(this.ADVERT_ERROR);
        }
        
        Pageable find = PageRequest.of(getAllPaymentsForAdvertRequest.getPage(), getAllPaymentsForAdvertRequest.getItems());
        Page <Payments> payments = paymentsRepository.findByAdvert(ad.get(), find);

        return payments.getContent();
    }

    public List<Payments> getAllPaymentsForCompany(GetAllPaymentsForCompanyRequest getAllPaymentsForCompanyRequest) throws GeneralExceptionHandler{

        Optional<Company> company = companyRepository.findById(getAllPaymentsForCompanyRequest.getCompanyId());
        if(!company.isPresent()){
            throw new GeneralExceptionHandler(this.COMPANY_ERROR);
        }
        
        Pageable find = PageRequest.of(getAllPaymentsForCompanyRequest.getPage(), getAllPaymentsForCompanyRequest.getItems());
        Page <Payments> payments = paymentsRepository.findByCompany(company.get(), find);

        return payments.getContent();
    }
}