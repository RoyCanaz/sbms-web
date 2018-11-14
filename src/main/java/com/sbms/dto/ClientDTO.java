/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class ClientDTO implements Serializable{
    
    private Long id;
    private Long   realId;
    private String clientType;
    private String name;
    private String description;
    private String website;
    private String address;
    private String email;
    private String phone; 
    private String branch;
    private Long createdBy;
    private User user;
    private Long companyId;
    private Company company;
    
   
    

    public ClientDTO() {
        
    }

    public ClientDTO(Long realId, String clientType, String name, String description, String website, String address, String email, String phone, String branch, Long createdBy) {
        this.realId = realId;
        this.clientType = clientType;
        this.name = name;
        this.description = description;
        this.website = website;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.branch = branch;
        this.createdBy = createdBy;
    }

    public ClientDTO(Long id, Long realId) {
        this.id = id;
        this.realId = realId;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    
    
   
    
    
    
    
    public Client getInstance(ClientDTO clientDTO){
        Client c = new Client(realId, clientType, name, description, website, address, email, phone, branch, user, company);
        return c;
    }
     
}
