/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

/**
 *
 * @author user
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "user")
public class User extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String password;
    @Transient  
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String userName;
    private String resetToken;
   
    @Transient
    private String displayName;
   
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", nullable = false)})
    private Set<UserRole> userRoles = new HashSet<>();
   
    @OneToMany(mappedBy = "user")
    private Set<AccountActivity> accountActivity = new HashSet<>();
    
    @ManyToOne
    private Company company;
    
    public User(){
        
    }
    public User(User user) {
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
    }

    public User(String password, String firstName, String lastName, String userName, Boolean active) {
        super(active);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
      
    }
    

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getDisplayName() {
        return lastName + " " + firstName;
    }
    @JsonIgnore
    public Set<UserRole> getDisplayUserRole() {
        return userRoles;
    }

    public Set<AccountActivity> getAccountActivity() {
        return accountActivity;
    }

    public void setAccountActivity(Set<AccountActivity> accountActivity) {
        this.accountActivity = accountActivity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    
   
    
}
