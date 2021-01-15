package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.roles.RolesExceptionHandler;
import com.authorization.authentication.models.Roles;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.roles.CreateRoleRequest;
import com.authorization.authentication.requests.roles.DeleteRoleRequest;
import com.authorization.authentication.requests.roles.GetParticularRoleRequest;
import com.authorization.authentication.requests.roles.GetParticularRoleUsersRequest;
import com.authorization.authentication.requests.roles.GetParticularUserRolesRequest;
import com.authorization.authentication.requests.roles.UpdateRoleRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.roles.CreateRoleResponse;
import com.authorization.authentication.responses.roles.GeneralPagedRolesResponse;
import com.authorization.authentication.responses.roles.GeneralRolesResponse;
import com.authorization.authentication.responses.roles.GetParticularRoleResponse;
import com.authorization.authentication.responses.users.GeneralPagedUsersResponse;
import com.authorization.authentication.services.RolesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    private CreateRoleResponse createRoleResponse = new CreateRoleResponse();

    private GeneralRolesResponse generalRolesResponse = new GeneralRolesResponse();

    private GeneralResponse generalResponse = new GeneralResponse();

    private GetParticularRoleResponse getParticularRoleResponse = new GetParticularRoleResponse();

    private GeneralPagedUsersResponse generalPagedUsersResponse = new GeneralPagedUsersResponse();

    private GeneralPagedRolesResponse generalPagedRolesResponse = new GeneralPagedRolesResponse();

    private String success = "Success";

    @RequestMapping(value = "createrole", method = RequestMethod.POST)
    public CreateRoleResponse createRole(@RequestBody CreateRoleRequest createRoleRequest) throws RolesExceptionHandler  {

        Roles roles = rolesService.createRole(createRoleRequest);
        createRoleResponse.setData(roles);
        createRoleResponse.setMessage(this.success);
        createRoleResponse.setStatus(200);
        return createRoleResponse;
    }

    @RequestMapping(value = "getallroles", method = RequestMethod.POST)
    public GeneralRolesResponse getAllRoles(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Roles> roles = rolesService.getAllRoles(generalPagedRequest);
        generalRolesResponse.setData(roles);
        generalRolesResponse.setMessage(success);
        generalRolesResponse.setStatus(200);
        return generalRolesResponse;
    }

    @RequestMapping(value = "deleterole", method = RequestMethod.DELETE)
    public GeneralResponse deleteRoles(@RequestBody DeleteRoleRequest deleteRoleRequest) throws RolesExceptionHandler {
        rolesService.deleteRole(deleteRoleRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularrole", method = RequestMethod.POST)
    public GetParticularRoleResponse getParticularRole(@RequestBody GetParticularRoleRequest getParticularRoleRequest) throws RolesExceptionHandler {

        Roles role = rolesService.getParticularRole(getParticularRoleRequest);
        getParticularRoleResponse.setData(role);
        getParticularRoleResponse.setMessage(success);
        getParticularRoleResponse.setStatus(200);
        return getParticularRoleResponse;
    }

    @RequestMapping(value = "getparticularroleusers", method = RequestMethod.POST)
    public GeneralPagedUsersResponse getParticularRoleUsers(@RequestBody GetParticularRoleUsersRequest getParticularRoleUsersRequest) throws RolesExceptionHandler {

        List <Users> users = rolesService.getParticularRoleUsers(getParticularRoleUsersRequest);
        generalPagedUsersResponse.setData(users);
        generalPagedUsersResponse.setMessage(success);
        generalPagedUsersResponse.setStatus(200);
        return generalPagedUsersResponse;
    }

    @RequestMapping(value = "getparticularuserroles", method = RequestMethod.POST)
    public GeneralPagedRolesResponse getParticularUserRoles(@RequestBody GetParticularUserRolesRequest getParticularUserRolesRequest) throws RolesExceptionHandler {

        List <Roles> roles = rolesService.getParticularUserRoles(getParticularUserRolesRequest);
        generalPagedRolesResponse.setData(roles);
        generalPagedRolesResponse.setMessage(success);
        generalPagedRolesResponse.setStatus(200);
        return generalPagedRolesResponse;
    }

    @RequestMapping(value = "updaterole", method = RequestMethod.PUT)
    public GetParticularRoleResponse updateRole(@RequestBody UpdateRoleRequest updateRoleRequest) throws RolesExceptionHandler {

        Roles role = rolesService.updateRole(updateRoleRequest);
        getParticularRoleResponse.setData(role);
        getParticularRoleResponse.setMessage(success);
        getParticularRoleResponse.setStatus(200);
        return getParticularRoleResponse;
    }
}