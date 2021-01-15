package com.authorization.authentication.controllers;

import java.util.List;

import com.authorization.authentication.exceptions.permissions.PermissionsExceptionHandler;
import com.authorization.authentication.models.Permissions;
import com.authorization.authentication.services.PermissionsService;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.permissions.CreatePermissionRequest;
import com.authorization.authentication.requests.permissions.DeletePermissionRequest;
import com.authorization.authentication.requests.permissions.GetParticularPermissionRequest;
import com.authorization.authentication.requests.permissions.UpdatePermissionsRequest;
import com.authorization.authentication.requests.roles.GetParticularRolePermissionsRequest;
import com.authorization.authentication.responses.GeneralResponse;
import com.authorization.authentication.responses.permissions.GeneralPagedPermissionsResponse;
import com.authorization.authentication.responses.permissions.GeneralPermissionsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/permissions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    private String success = "success";

    private GeneralPermissionsResponse generalPermissionsResponse = new GeneralPermissionsResponse();

    private GeneralPagedPermissionsResponse generalPagedPermissionsResponse = new GeneralPagedPermissionsResponse();

    private GeneralResponse generalResponse = new GeneralResponse();
    
    @RequestMapping(value = "createpermission", method = RequestMethod.POST)
    public GeneralPermissionsResponse createPermission(@RequestBody CreatePermissionRequest createPermissionRequest) {

        Permissions permission = permissionsService.createPermission(createPermissionRequest);
        generalPermissionsResponse.setData(permission);
        generalPermissionsResponse.setMessage("Success");
        generalPermissionsResponse.setStatus(200);
        return generalPermissionsResponse;
    }

    @RequestMapping(value = "getallpermissions", method = RequestMethod.POST)
    public GeneralPagedPermissionsResponse getAllRoles(@RequestBody GeneralPagedRequest generalPagedRequest){

        List <Permissions> permissions = permissionsService.getAllPermissions(generalPagedRequest);
        generalPagedPermissionsResponse.setData(permissions);
        generalPagedPermissionsResponse.setMessage(success);
        generalPagedPermissionsResponse.setStatus(200);
        return generalPagedPermissionsResponse;
    }

    @RequestMapping(value = "deletepermission", method = RequestMethod.DELETE)
    public GeneralResponse deletePermission(@RequestBody DeletePermissionRequest deletePermissionRequest) throws PermissionsExceptionHandler {
        permissionsService.deletePermission(deletePermissionRequest);
        generalResponse.setMessage(success);
        generalResponse.setStatus(200);
        return generalResponse;
    }

    @RequestMapping(value = "getparticularpermission", method = RequestMethod.POST)
    public GeneralPermissionsResponse getParticularPermission(@RequestBody GetParticularPermissionRequest getParticularPermissionRequest) throws PermissionsExceptionHandler {

        Permissions permission = permissionsService.getParticularPermission(getParticularPermissionRequest);
        generalPermissionsResponse.setData(permission);
        generalPermissionsResponse.setMessage("Success");
        generalPermissionsResponse.setStatus(200);
        return generalPermissionsResponse;
    }

    @RequestMapping(value = "getparticularrolepermissions", method = RequestMethod.POST)
    public GeneralPagedPermissionsResponse getParticularRolePermissions(@RequestBody GetParticularRolePermissionsRequest getParticularRolePermissionRequest) throws PermissionsExceptionHandler {

        List <Permissions> permission = permissionsService.getParticularRolePermissions(getParticularRolePermissionRequest);
        generalPagedPermissionsResponse.setData(permission);
        generalPagedPermissionsResponse.setMessage(success);
        generalPagedPermissionsResponse.setStatus(200);
        return generalPagedPermissionsResponse;
    }

    @RequestMapping(value = "updatepermission", method = RequestMethod.PUT)
    public GeneralPermissionsResponse updatePermission(@RequestBody UpdatePermissionsRequest updatePermissionRequest) throws PermissionsExceptionHandler {

        Permissions permission = permissionsService.updatePermission(updatePermissionRequest);
        generalPermissionsResponse.setData(permission);
        generalPermissionsResponse.setMessage(success);
        generalPermissionsResponse.setStatus(200);
        return generalPermissionsResponse;
    }
}