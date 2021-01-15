package com.retargeting_branding.controllers;

import java.util.List;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.models.Invoice;
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
import com.retargeting_branding.responses.GeneralResponse;
import com.retargeting_branding.responses.invoice.GeneralInvoiceResponse;
import com.retargeting_branding.responses.invoice.GeneralPagedInvoicesResponse;
import com.retargeting_branding.services.InvoiceService;
import com.retargeting_branding.utils.CommonMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/invoice")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InvoiceController extends CommonMethods{

    @Autowired
    private InvoiceService invoiceService;

    private GeneralPagedInvoicesResponse generalPagedInvoicesResponse = new GeneralPagedInvoicesResponse();

    private GeneralInvoiceResponse generalInvoiceResponse = new GeneralInvoiceResponse();
    
    @RequestMapping(value = "createinvoice", method = RequestMethod.POST)
    public GeneralResponse createInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest) throws GeneralExceptionHandler{

        invoiceService.createInvoice(createInvoiceRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getallinvoices", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoices(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Invoice> invoice = invoiceService.getAllInvoices(generalPagedRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "deleteinvoice", method = RequestMethod.DELETE)
    public GeneralResponse deleteInvoice(@RequestBody GeneralDeleteRequest generalDeleteRequest) throws GeneralExceptionHandler {
        invoiceService.deleteInvoice(generalDeleteRequest);
        generalResponse.setMessage(this.SUCCESS);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "updateinvoice", method = RequestMethod.PUT)
    public GeneralInvoiceResponse updateInvoice(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) throws GeneralExceptionHandler {

        Invoice invoice = invoiceService.updateInvoice(updateInvoiceRequest);
        generalInvoiceResponse.setData(invoice);
        generalInvoiceResponse.setMessage(this.SUCCESS);
        generalInvoiceResponse.setStatus(200);
        return generalInvoiceResponse;
    }

    @RequestMapping(value = "getinvoice", method = RequestMethod.POST)
    public GeneralInvoiceResponse getInvoice(@RequestBody GeneralParticularRequest generalParticularRequest) throws GeneralExceptionHandler {

        Invoice invoice = invoiceService.getInvoice(generalParticularRequest);
        generalInvoiceResponse.setData(invoice);
        generalInvoiceResponse.setMessage(this.SUCCESS);
        generalInvoiceResponse.setStatus(200);
        return generalInvoiceResponse;
    }

    @RequestMapping(value = "getallinvoicesforcompany", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesForCompany(@RequestBody GetAllInvoicesForCompanyRequest getAllInvoicesForCompanyRequest) throws GeneralExceptionHandler {

        List <Invoice> invoice = invoiceService.getAllInvoicesForCompany(getAllInvoicesForCompanyRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbyinvoicestatus", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByInvoiceStatus(@RequestBody GetAllInvoicesByInvoiceStatusRequest getAllInvoicesByInvoiceStatusRequest) {

        List <Invoice> invoice = invoiceService.getAllInvoicesByInvoiceStatus(getAllInvoicesByInvoiceStatusRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbyinvoicestatusforadvert", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByInvoiceStatusForAdvert(@RequestBody GetAllInvoicesByInvoiceStatusForAdvertRequest getAllInvoicesByInvoiceStatusForAdvertRequest) throws GeneralExceptionHandler{

        List <Invoice> invoice = invoiceService.getAllInvoicesByInvoiceStatusForAdvert(getAllInvoicesByInvoiceStatusForAdvertRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbyinvoicestatusforcompany", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByInvoiceStatusForCompany(@RequestBody GetAllInvoicesByInvoiceStatusForCompanyRequest getAllInvoicesByInvoiceStatusForCompanyRequest) throws GeneralExceptionHandler{

        List <Invoice> invoice = invoiceService.getAllInvoicesByInvoiceStatusForCompany(getAllInvoicesByInvoiceStatusForCompanyRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbypaymentmode", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByPaymentMode(@RequestBody GetAllInvoicesByPaymentModeRequest getAllInvoicesByPaymentModeRequest) {

        List <Invoice> invoice = invoiceService.getAllInvoicesByPaymentMode(getAllInvoicesByPaymentModeRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbypaymentmodeforadvert", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByPaymentModeForAdvert(@RequestBody GetAllInvoicesByPaymentModeForAdvertRequest getAllInvoicesByPaymentModeForAdvertRequest) throws GeneralExceptionHandler{

        List <Invoice> invoice = invoiceService.getAllInvoicesByPaymentModeForAdvert(getAllInvoicesByPaymentModeForAdvertRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }

    @RequestMapping(value = "getallinvoicesbypaymentmodeforcompany", method = RequestMethod.POST)
    public GeneralPagedInvoicesResponse getAllInvoicesByPaymentModeForCompany(@RequestBody GetAllInvoicesByPaymentModeForCompanyRequest getAllInvoicesByPaymentModeForCompanyRequest) throws GeneralExceptionHandler{

        List <Invoice> invoice = invoiceService.getAllInvoicesByPaymentModeForCompany(getAllInvoicesByPaymentModeForCompanyRequest);
        generalPagedInvoicesResponse.setData(invoice);
        generalPagedInvoicesResponse.setMessage(this.SUCCESS);
        generalPagedInvoicesResponse.setStatus(200);
        return generalPagedInvoicesResponse;
    }
}