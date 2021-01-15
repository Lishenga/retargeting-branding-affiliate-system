package com.authorization.authentication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.authorization.authentication.exceptions.roles.RolesExceptionHandler;
import com.authorization.authentication.models.Roles;
import com.authorization.authentication.models.Users;
import com.authorization.authentication.repositories.RolesRepository;
import com.authorization.authentication.repositories.UsersRepository;
import com.authorization.authentication.requests.GeneralPagedRequest;
import com.authorization.authentication.requests.roles.CreateRoleRequest;
import com.authorization.authentication.requests.roles.DeleteRoleRequest;
import com.authorization.authentication.requests.roles.GetParticularRoleRequest;
import com.authorization.authentication.requests.roles.GetParticularRoleUsersRequest;
import com.authorization.authentication.requests.roles.GetParticularUserRolesRequest;
import com.authorization.authentication.requests.roles.UpdateRoleRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsersRepository usersRepository;

    private String roleError = "Role not found";

    private String userError = "User not found";


    public Roles createRole(CreateRoleRequest createRoleRequest) throws RolesExceptionHandler {

        Optional <Users> user = usersRepository.findById(createRoleRequest.getCreatedBy());

        if(!user.isPresent()){
            throw new RolesExceptionHandler(this.userError);
        }

        Roles roles = new Roles();
        roles.createRole(createRoleRequest);
        roles.setCreatedBy(user.get());
        roles.setUpdatedBy(user.get());
        rolesRepository.save(roles);
        
        return roles;
    }

    public List <Roles> getAllRoles(GeneralPagedRequest generalPagedRequest) {

        Pageable find = PageRequest.of(generalPagedRequest.getPage(), generalPagedRequest.getItems());
        Page <Roles> roles = rolesRepository.findByIsDeleted(generalPagedRequest.getIsDeleted(), find);

        return roles.getContent();
    }

    public void deleteRole(DeleteRoleRequest deleteRoleRequest) throws RolesExceptionHandler {

        Optional <Roles> find = rolesRepository.findById(deleteRoleRequest.getRoleId());

        if(!find.isPresent()){
            throw new RolesExceptionHandler(this.roleError);
        }

        Roles role = find.get();
        role.setIsDeleted(true);
        role.setDeletedAt(LocalDateTime.now());
        rolesRepository.save(role);
    }

    public Roles getParticularRole(GetParticularRoleRequest getParticularRoleRequest) throws RolesExceptionHandler {

        Optional <Roles> role = rolesRepository.findById(getParticularRoleRequest.getRoleId());

        if(!role.isPresent()){
            throw new RolesExceptionHandler(this.roleError);
        }

        return role.get();
    }

    public List <Users> getParticularRoleUsers(GetParticularRoleUsersRequest getParticularRoleUsersRequest) throws RolesExceptionHandler {

        Optional <Roles> role = rolesRepository.findById(getParticularRoleUsersRequest.getRoleId());

        if(!role.isPresent()){
            throw new RolesExceptionHandler(this.roleError);
        }

        Optional <Users> user = usersRepository.findByRoles(role.get());

        if(!user.isPresent()){
            throw new RolesExceptionHandler(this.userError);
        }

        Pageable find = PageRequest.of(getParticularRoleUsersRequest.getPage(), getParticularRoleUsersRequest.getItems());
        Page <Users> users = usersRepository.findByIsDeletedAndRoles(getParticularRoleUsersRequest.getIsDeleted(), role.get(), find);

        return users.getContent();
    }

    public List <Roles> getParticularUserRoles(GetParticularUserRolesRequest getParticularUserRolesRequest) throws RolesExceptionHandler {

        Optional <Users> user = usersRepository.findById(getParticularUserRolesRequest.getUserId());

        if(!user.isPresent()){
            throw new RolesExceptionHandler(this.userError);
        }

        return rolesRepository.findUserRoles(getParticularUserRolesRequest.getUserId());
    }

    public Roles updateRole(UpdateRoleRequest updateRoleRequest) throws RolesExceptionHandler {

        Optional <Roles> role = rolesRepository.findById(updateRoleRequest.getRoleId());

        if(!role.isPresent()){
            throw new RolesExceptionHandler(this.roleError);
        }

        Optional <Users> user = usersRepository.findById(updateRoleRequest.getUpdatedBy());

        if(!user.isPresent()){
            throw new RolesExceptionHandler(this.userError);
        }

        Roles rol = role.get();

        if(updateRoleRequest.getName() != null){
            rol.setName(updateRoleRequest.getName());
        }

        rol.setUpdatedAt(LocalDateTime.now());
        rol.setUpdatedBy(user.get());

        rolesRepository.save(rol);

        return rol;
    }
}