package com.authorization.authentication.requests.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.authorization.authentication.enums.UsersType;
import com.authorization.authentication.requests.address.UpdateUserAddress;

public class UpdateUserDetailsRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 664729482738514778L;
    
    @NotNull(message = "Provide userId")
    private Long userId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Long roleId;

    private UsersType userType;

    private String password;

    private UpdateUserAddress updateUserAddress;   

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public UsersType getUserType() {
        return this.userType;
    }

    public void setUserType(UsersType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateUserAddress getUpdateUserAddress() {
        return this.updateUserAddress;
    }

    public void setUpdateUserAddress(UpdateUserAddress updateUserAddress) {
        this.updateUserAddress = updateUserAddress;
    }
}