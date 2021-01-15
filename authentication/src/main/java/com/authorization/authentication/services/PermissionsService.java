package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.permissions.PermissionsExceptionHandler;
import com.authorization.authentication.models.Permissions;
import com.authorization.authentication.models.Roles;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.PermissionsRepository;
import com.authorization.authentication.repositories.RolesRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.permissions.CreatePermissionRequest;
import com.authorization.authentication.requests.permissions.DeletePermissionRequest;
import com.authorization.authentication.requests.permissions.GetParticularPermissionRequest;
import com.authorization.authentication.requests.permissions.UpdatePermissionsRequest;
import com.authorization.authentication.requests.roles.GetParticularRolePermissionsRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;
    
    public Permissions createPermission(CreatePermissionRequest createPermissionRequest) {

        Permissions permission = new Permissions();
        permission.createPermission(createPermissionRequest);
        permissionsRepository.save(permission);
        
        return permission;
    }

    public List <Permissions> getAllPermissions(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Permissions> permissions = permissionsRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return permissions.getContent();
    }

    public void deletePermission(DeletePermissionRequest deletePermissionRequest) throws PermissionsExceptionHandler {

        Optional <Permissions> find = permissionsRepository.findById(deletePermissionRequest.getPermissionId());

        if(!find.isPresent()){
            throw new PermissionsExceptionHandler("Permission not found");
        }

        Permissions permission = find.get();
        permission.setIsDeleted(true);
        permission.setDeletedAt(LocalDateTime.now());
        permissionsRepository.save(permission);
    }

    public Permissions getParticularPermission(GetParticularPermissionRequest getParticularPermissionRequest) throws PermissionsExceptionHandler {

        Optional <Permissions> permission = permissionsRepository.findById(getParticularPermissionRequest.getPermissionId());

        if(!permission.isPresent()){
            throw new PermissionsExceptionHandler("Permission not Found");
        }

        return permission.get();
    }

    public List <Permissions> getParticularRolePermissions(GetParticularRolePermissionsRequest getParticularRolesPermissionsRequest) throws PermissionsExceptionHandler {

        Optional <Roles> role = rolesRepository.findById(getParticularRolesPermissionsRequest.getRoleId());

        if(!role.isPresent()){
            throw new PermissionsExceptionHandler("Role not Found");
        }

        Pageable find = PageRequest.of(getParticularRolesPermissionsRequest.getPage(), getParticularRolesPermissionsRequest.getItems());
        Page <Permissions> permissions = permissionsRepository.findByRoles(role.get(), getParticularRolesPermissionsRequest.getIsDeleted(), find);

        return permissions.getContent();
    }

    public Permissions updatePermission(UpdatePermissionsRequest updatePermissionsRequest) throws PermissionsExceptionHandler {

        Optional <Users> user = usersRepository.findById(updatePermissionsRequest.getUserId());

        if(!user.isPresent()){
            throw new PermissionsExceptionHandler("User not Found");
        }

        Optional <Permissions> permis = permissionsRepository.findById(updatePermissionsRequest.getPermissionId());

        if(!permis.isPresent()){
            throw new PermissionsExceptionHandler("Permission not Found");
        }

        Permissions permission = permis.get();

        if(updatePermissionsRequest.getName() != null){
            permission.setName(updatePermissionsRequest.getName());
        }

        if(updatePermissionsRequest.getRoleId() != null){
            Optional <Roles> role = rolesRepository.findById(updatePermissionsRequest.getRoleId());

            if(!role.isPresent()){
                throw new PermissionsExceptionHandler("Role not Found");
            }
        }

        permission.setUpdatedBy(user.get());
        permission.setUpdatedAt(LocalDateTime.now());

        permissionsRepository.save(permission);

        return permission;
    }
}