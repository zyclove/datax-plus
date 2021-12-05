package com.datax.plus.model;

import java.util.List;

public class User extends BaseVO{
    private int accountId;
    private String username;
    private String password;
    private int status;
    private int userType;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    private List<String> roles;




    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
    //
//    tokenPayload.AccountId = result.AccountId
//    tokenPayload.MenuItems = result.FunStr
//    tokenPayload.PageItems = result.ItemStr
//    tokenPayload.Status = result.Status
//    tokenPayload.UserType = result.UserType

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
