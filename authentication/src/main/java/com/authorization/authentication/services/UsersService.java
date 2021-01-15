package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.users.UsersExceptionHandler;
import com.authorization.authentication.models.Address;
import com.authorization.authentication.models.Roles;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.AddressRepository;
import com.authorization.authentication.repositories.RolesRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.address.UpdateUserAddress;
import com.authorization.authentication.requests.users.DeleteUserRequest;
import com.authorization.authentication.requests.users.GetParticularUserRequest;
import com.authorization.authentication.requests.users.GetUsersByUserTypeRequest;
import com.authorization.authentication.requests.users.ResetUserPasswordRequest;
import com.authorization.authentication.requests.users.UpdateUserDetailsRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private AddressRepository addressRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String error = "User not found";

    public List <Users> getAllUsers(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Users> users = usersRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return users.getContent();
    }

    public void deleteUser(DeleteUserRequest deleteUserRequest) throws UsersExceptionHandler {

        Optional <Users> find = usersRepository.findById(deleteUserRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();
        user.setIsDeleted(true);
        usersRepository.save(user);
    }

    public Users getParticularUser(GetParticularUserRequest getParticularUserRequest) throws UsersExceptionHandler {

        Optional <Users> user = usersRepository.findById(getParticularUserRequest.getUserId());

        if(!user.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        return user.get();
    }

    public List <Users> getUsersByUserType(GetUsersByUserTypeRequest getUsersByUserTypeRequest) {

        Pageable find = PageRequest.of(getUsersByUserTypeRequest.getPage(), getUsersByUserTypeRequest.getItems());
        Page <Users> users = usersRepository.findByUserTypeAndIsDeleted(getUsersByUserTypeRequest.getUserType(), getUsersByUserTypeRequest.getIsDeleted(), find);

        return users.getContent();
    }

    public Users updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) throws UsersExceptionHandler {
        /**
         * Encrypt Passwords
         */
        Optional <Users> find = usersRepository.findById(updateUserDetailsRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();

        if(updateUserDetailsRequest.getEmail() != null){
            user.setEmail(updateUserDetailsRequest.getEmail());
        }

        if(updateUserDetailsRequest.getPassword() != null){
            user.setPassword(passwordEncoder.encode(updateUserDetailsRequest.getPassword()));
        }

        if(updateUserDetailsRequest.getFirstName() != null){
            user.setFirstName(updateUserDetailsRequest.getFirstName());
        }

        if(updateUserDetailsRequest.getLastName() != null){
            user.setLastName(updateUserDetailsRequest.getLastName());
        }

        if(updateUserDetailsRequest.getPhoneNumber() != null){
            user.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
        }

        if(updateUserDetailsRequest.getUserType() != null){
            user.setUserType(updateUserDetailsRequest.getUserType());
        }

        if(updateUserDetailsRequest.getRoleId() != null){
            Optional <Roles> role = rolesRepository.findById(updateUserDetailsRequest.getRoleId());

            if(!role.isPresent()){
                throw new UsersExceptionHandler("Role does not exist");
            }
        }

        if(updateUserDetailsRequest.getUpdateUserAddress() != null){
            Optional <Address> findAdd = addressRepository.findByUser(user);

            if(!findAdd.isPresent()){
                throw new UsersExceptionHandler("Address does not exist");
            }

            UpdateUserAddress update = updateUserDetailsRequest.getUpdateUserAddress();

            Address address = findAdd.get();

            if(update.getCity() != null){
                address.setCity(update.getCity());
            }

            if(update.getState() != null){
                address.setState(update.getState());
            }

            if(update.getPostalCode() != null){
                address.setPostalCode(update.getPostalCode());
            }

            if(update.getStreet() != null){
                address.setStreet(update.getStreet());
            }

            if(update.getCountry() != null){
                address.setCountry(update.getCountry());
            }

            address.setUpdatedBy(user);

            address.setUpdatedAt(LocalDateTime.now());

            addressRepository.save(address);
        }

        user.setUpdatedBy(user);

        user.setUpdatedAt(LocalDateTime.now());

        usersRepository.save(user);
        
        return user;
    }

    public void resetUserPassword(ResetUserPasswordRequest resetUserPasswordRequest) throws UsersExceptionHandler{

        Optional <Users> find = usersRepository.findById(resetUserPasswordRequest.getUserId());

        if(!find.isPresent()){
            throw new UsersExceptionHandler(this.error);
        }

        Users user = find.get();
        user.setPassword(passwordEncoder.encode(resetUserPasswordRequest.getPassword()));
        usersRepository.save(user);
    }
}