package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.address.AddressExceptionHandler;
import com.authorization.authentication.exceptions.security.SecurityExceptionHandler;
import com.authorization.authentication.models.Address;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.AddressRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.address.DeleteUserAddressRequest;
import com.authorization.authentication.requests.address.GetParticularUserAddressRequest;
import com.authorization.authentication.requests.address.UpdateUserAddressRequest;
import com.authorization.authentication.requests.users.RegisterUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void saveAddress(Users user, RegisterUserRequest registerUserRequest) throws SecurityExceptionHandler {

        Address address = new Address();
        address.saveAddress(user, registerUserRequest);
        addressRepository.save(address);
        
    }

    public List <Address> getAllAddresses(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Address> addresses = addressRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return addresses.getContent();
    }

    public void deleteUserAddress(DeleteUserAddressRequest deleteUserAddressRequest) throws AddressExceptionHandler {

        Optional <Users> user = usersRepository.findById(deleteUserAddressRequest.getUserId());

        if(!user.isPresent()){
            throw new AddressExceptionHandler("User not found");
        }

        Optional <Address> address = addressRepository.findByUser(user.get());

        if(!address.isPresent()){
            throw new AddressExceptionHandler("Address not found");
        }

        Address location = address.get();
        location.setIsDeleted(true);
        location.setDeletedAt(LocalDateTime.now());
        addressRepository.save(location);
    }

    public Address getParticularUserAddress(GetParticularUserAddressRequest getParticularUserAddressRequest) throws AddressExceptionHandler {

        Optional <Address> address = addressRepository.findByUserAndIsDeleted(getParticularUserAddressRequest.getUser(), getParticularUserAddressRequest.getIsDeleted());

        if(!address.isPresent()){
            throw new AddressExceptionHandler("Address not Found");
        }

        return address.get();
    }

    public Address updateUserAddress(UpdateUserAddressRequest updateUserAddressRequest) throws AddressExceptionHandler {

        Optional <Users> user = usersRepository.findById(updateUserAddressRequest.getUserId());

        if(!user.isPresent()){
            throw new AddressExceptionHandler("User not Found");
        }

        Optional <Address> address = addressRepository.findByUser(user.get());

        if(!address.isPresent()){
            throw new AddressExceptionHandler("User with address not Found");
        }

        Address add = address.get();

        if(updateUserAddressRequest.getCity() != null){
            add.setCity(updateUserAddressRequest.getCity());
        }

        if(updateUserAddressRequest.getCountry() != null){
            add.setCountry(updateUserAddressRequest.getCountry());
        }

        if(updateUserAddressRequest.getState() != null){
            add.setState(updateUserAddressRequest.getState());
        }

        if(updateUserAddressRequest.getStreet() != null){
            add.setStreet(updateUserAddressRequest.getStreet());
        }

        if(updateUserAddressRequest.getPostalCode() != null){
            add.setPostalCode(updateUserAddressRequest.getPostalCode());
        }

        add.setUpdatedBy(user.get());
        add.setUpdatedAt(LocalDateTime.now());

        addressRepository.save(add);

        return add;
    }
}