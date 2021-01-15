package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.address.AddressExceptionHandler;
import com.authorization.authentication.models.Address;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.address.DeleteUserAddressRequest;
import com.authorization.authentication.requests.address.GetParticularUserAddressRequest;
import com.authorization.authentication.requests.address.UpdateUserAddressRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.address.GeneralAddressResponse;
import com.authorization.authentication.responses.address.GeneralPagedAddressesResponse;
import com.authorization.authentication.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    private GeneralPagedAddressesResponse generalPagedAddressesResponse = new GeneralPagedAddressesResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    private GeneralAddressResponse generalAddressResponse = new GeneralAddressResponse();

    private String success = "Success";

    @RequestMapping(value = "getalladdresses", method = RequestMethod.POST)
    public GeneralPagedAddressesResponse getAllAddresses(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Address> addresses = addressService.getAllAddresses(generalPagedRequest);
        generalPagedAddressesResponse.setData(addresses);
        generalPagedAddressesResponse.setMessage(success);
        generalPagedAddressesResponse.setStatus(200);
        return generalPagedAddressesResponse;
    }

    @RequestMapping(value = "updateuseraddress", method = RequestMethod.PUT)
    public GeneralAddressResponse updateUserAddress(@RequestBody UpdateUserAddressRequest updateUserAddressRequest) throws AddressExceptionHandler{

        Address address = addressService.updateUserAddress(updateUserAddressRequest);
        generalAddressResponse.setData(address);
        generalAddressResponse.setMessage(success);
        generalAddressResponse.setStatus(200);
        return generalAddressResponse;
    }

    @RequestMapping(value = "deleteuseraddress", method = RequestMethod.DELETE)
    public GeneralResponse deleteUserAddress(@RequestBody DeleteUserAddressRequest deleteUserAddressRequest) throws AddressExceptionHandler {
        addressService.deleteUserAddress(deleteUserAddressRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularuseraddress", method = RequestMethod.POST)
    public GeneralAddressResponse getParticularUserAddress(@RequestBody GetParticularUserAddressRequest getParticularUserAddressRequest) throws AddressExceptionHandler {

        Address address = addressService.getParticularUserAddress(getParticularUserAddressRequest);
        generalAddressResponse.setData(address);
        generalAddressResponse.setMessage(this.success);
        generalAddressResponse.setStatus(200);
        return generalAddressResponse;
    }
}