package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.users.UsersExceptionHandler;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.users.DeleteUserRequest;
import com.authorization.authentication.requests.users.GetParticularUserRequest;
import com.authorization.authentication.requests.users.GetUsersByUserTypeRequest;
import com.authorization.authentication.requests.users.ResetUserPasswordRequest;
import com.authorization.authentication.requests.users.UpdateUserDetailsRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.users.GeneralPagedUsersResponse;
import com.authorization.authentication.responses.users.GeneralUsersResponse;
import com.authorization.authentication.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    private String success = "Success";

    private GeneralUsersResponse generalUsersResponse = new GeneralUsersResponse();

    private GeneralPagedUsersResponse generalPagedUsersResponse = new GeneralPagedUsersResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    @RequestMapping(value = "getparticularuser", method = RequestMethod.POST)
    public GeneralUsersResponse getParticularUser(@RequestBody GetParticularUserRequest getParticularUserRequest) throws UsersExceptionHandler  {

        Users user = usersService.getParticularUser(getParticularUserRequest);
        generalUsersResponse.setData(user);
        generalUsersResponse.setMessage(this.success);
        generalUsersResponse.setStatus(200);
        return generalUsersResponse;
    }

    @RequestMapping(value = "getAllUsers", method = RequestMethod.POST)
    public GeneralPagedUsersResponse getAllUsers(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Users> users = usersService.getAllUsers(generalPagedRequest);
        generalPagedUsersResponse.setData(users);
        generalPagedUsersResponse.setMessage(success);
        generalPagedUsersResponse.setStatus(200);
        return generalPagedUsersResponse;
    }

    @RequestMapping(value = "deleteuser", method = RequestMethod.DELETE)
    public GeneralResponse deleteUser(@RequestBody DeleteUserRequest deleteUserRequest) throws UsersExceptionHandler {
        usersService.deleteUser(deleteUserRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getuserbyusertype", method = RequestMethod.POST)
    public GeneralPagedUsersResponse getUsersByUserType(@RequestBody GetUsersByUserTypeRequest getUsersByUserTypeRequest){

        List <Users> users = usersService.getUsersByUserType(getUsersByUserTypeRequest);
        generalPagedUsersResponse.setData(users);
        generalPagedUsersResponse.setMessage(success);
        generalPagedUsersResponse.setStatus(200);
        return generalPagedUsersResponse;
    }

    @RequestMapping(value = "updateuserdetails", method = RequestMethod.PUT)
    public GeneralUsersResponse updateUserDetails(@RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) throws UsersExceptionHandler{

        Users user = usersService.updateUserDetails(updateUserDetailsRequest);
        generalUsersResponse.setData(user);
        generalUsersResponse.setMessage(this.success);
        generalUsersResponse.setStatus(200);
        return generalUsersResponse;
    }

    @RequestMapping(value = "resetuserpassword", method = RequestMethod.POST)
    public GeneralResponse resetUserPassword(@RequestBody ResetUserPasswordRequest resetUserPasswordRequest) throws UsersExceptionHandler{

        usersService.resetUserPassword(resetUserPasswordRequest);
        generalResponse.setMessage(this.success);
        generalResponse.setStatus(200);
        return generalResponse;
    }
}