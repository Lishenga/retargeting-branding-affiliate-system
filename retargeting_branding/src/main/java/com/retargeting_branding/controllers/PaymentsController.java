package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Payments;
import com.retargeting_branding.requests.general.GeneralDeleteRequest;
import com.retargeting_branding.requests.general.GeneralPagedRequest;
import com.retargeting_branding.requests.general.GeneralParticularRequest;
import com.retargeting_branding.requests.payments.CreatePaymentRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentByPaymentModeRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsByPaymentStatusRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsForAdvertRequest;
import com.retargeting_branding.requests.payments.GetAllPaymentsForCompanyRequest;
import com.retargeting_branding.requests.payments.UpdatePaymentRequest;
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.payments.GeneralPagedPaymentsResponse;
import com.retargeting_branding.responses.payments.GeneralPaymentResponse;
import com.retargeting_branding.services.PaymentsService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentsController extends CommonMethods{

    @Autowired
    private PaymentsService paymentsService;

    private GeneralPagedPaymentsResponse generalPagedPaymentsResponse = new GeneralPagedPaymentsResponse();

    private GeneralPaymentResponse generalPaymentResponse = new GeneralPaymentResponse();
    
    @RequestMapping(value = "createpayment", method = RequestMethod.POST)
    public GeneralResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest) throws GeneralExceptionHandler{

        paymentsService.createPayment(createPaymentRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallpayments", method = RequestMethod.POST)
    public GeneralPagedPaymentsResponse getAllPayments(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Payments> payments = paymentsService.getAllPayments(generalPagedRequest);
        generalPagedPaymentsResponse.setData(payments);
        generalPagedPaymentsResponse.setMessage(this.SUCCESS);
        generalPagedPaymentsResponse.setStatus(200);
        return generalPagedPaymentsResponse;
    }

    @RequestMapping(value = "deletepayment", method = RequestMethod.DELETE)
    public GeneralResponse deletePayment(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        paymentsService.deletePayment(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updatepayment", method = RequestMethod.PATCH)
    public GeneralPaymentResponse updatePayment(@RequestBody UpdatePaymentRequest updatePaymentRequest) throws GeneralExceptionHandler {

        Payments payment = paymentsService.updatePayment(updatePaymentRequest);
        generalPaymentResponse.setData(payment);
        generalPaymentResponse.setMessage(this.SUCCESS);
        generalPaymentResponse.setStatus(200);
        return generalPaymentResponse;
    }

    @RequestMapping(value = "getpayment", method = RequestMethod.POST)
    public GeneralPaymentResponse getPayment(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        Payments payment = paymentsService.getPayment(generalParticularRequest);
        generalPaymentResponse.setData(payment);
        generalPaymentResponse.setMessage(this.SUCCESS);
        generalPaymentResponse.setStatus(200);
        return generalPaymentResponse;
    }

    @RequestMapping(value = "getpaymentforinvoice", method = RequestMethod.POST)
    public GeneralPaymentResponse getPaymentForInvoice(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        Payments payment = paymentsService.getPaymentForInvoice(generalParticularRequest);
        generalPaymentResponse.setData(payment);
        generalPaymentResponse.setMessage(this.SUCCESS);
        generalPaymentResponse.setStatus(200);
        return generalPaymentResponse;
    }

    @RequestMapping(value = "getallpaymentsbypaymentstatus", method = RequestMethod.POST)
    public GeneralPagedPaymentsResponse getAllPaymentByPaymentStatus(@RequestBody GetAllPaymentsByPaymentStatusRequest getAllPaymentsByPaymentStatusRequest) {

        List <Payments> payments = paymentsService.getAllPaymentByPaymentStatus(getAllPaymentsByPaymentStatusRequest);
        generalPagedPaymentsResponse.setData(payments);
        generalPagedPaymentsResponse.setMessage(this.SUCCESS);
        generalPagedPaymentsResponse.setStatus(200);
        return generalPagedPaymentsResponse;
    }

    @RequestMapping(value = "getallpaymentsbypaymentmode", method = RequestMethod.POST)
    public GeneralPagedPaymentsResponse getAllPaymentByPaymentMode(@RequestBody GetAllPaymentByPaymentModeRequest getAllPaymentByPaymentModeRequest) {

        List <Payments> payments = paymentsService.getAllPaymentByPaymentMode(getAllPaymentByPaymentModeRequest);
        generalPagedPaymentsResponse.setData(payments);
        generalPagedPaymentsResponse.setMessage(this.SUCCESS);
        generalPagedPaymentsResponse.setStatus(200);
        return generalPagedPaymentsResponse;
    }

    @RequestMapping(value = "getallpaymentsforadvert", method = RequestMethod.POST)
    public GeneralPagedPaymentsResponse getAllPaymentsForAdvert(@RequestBody GetAllPaymentsForAdvertRequest getAllPaymentsForAdvertRequest) throws GeneralExceptionHandler{

        List <Payments> payments = paymentsService.getAllPaymentsForAdvert(getAllPaymentsForAdvertRequest);
        generalPagedPaymentsResponse.setData(payments);
        generalPagedPaymentsResponse.setMessage(this.SUCCESS);
        generalPagedPaymentsResponse.setStatus(200);
        return generalPagedPaymentsResponse;
    }

    @RequestMapping(value = "getallpaymentsforcompany", method = RequestMethod.POST)
    public GeneralPagedPaymentsResponse getAllPaymentsForCompany(@RequestBody GetAllPaymentsForCompanyRequest getAllPaymentsForCompanyRequest) throws GeneralExceptionHandler{

        List <Payments> payments = paymentsService.getAllPaymentsForCompany(getAllPaymentsForCompanyRequest);
        generalPagedPaymentsResponse.setData(payments);
        generalPagedPaymentsResponse.setMessage(this.SUCCESS);
        generalPagedPaymentsResponse.setStatus(200);
        return generalPagedPaymentsResponse;
    }
}