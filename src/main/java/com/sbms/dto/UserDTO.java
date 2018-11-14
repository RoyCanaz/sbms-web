/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.User;

/**
 *
 * @author user
 */
public class UserDTO {
    
    private Long id;
    private Boolean active;
    private String password;
    private String firstName;
    private String lastName;
    private String userName;
    private Long companyId;
    private String companyName;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Long id, Boolean active, String password, String firstName, String lastName, String userName, Long companyId, String companyName, String role) {
        this.id = id;
        this.active = active;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.companyId = companyId;
        this.role = role;
        this.companyName = companyName;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
 /*   public User getInstance(UserDTO userDTO){
        User user = new User(password, firstName, lastName, userName, active);
        return user;
    }
  */  

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
