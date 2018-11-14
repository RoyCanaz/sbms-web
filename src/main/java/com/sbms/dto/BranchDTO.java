/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Branch;
import com.sbms.domain.Client;
import com.sbms.domain.User;

/**
 *
 * @author user
 */
public class BranchDTO {
     private Long id;
     private Long realId;
     private String name;
     private String description;
     private String address;
     private Client client;
     private Long clientId;
     private User user;
     private Long createdBy;

    public BranchDTO() {
    }

    public BranchDTO(Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }
    

    public BranchDTO(Long realId, String name, String description, String address, Long clientId, Long createdBy) {
        this.realId = realId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.clientId = clientId;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Branch getInstance(BranchDTO bdto){
        Branch b = new Branch(address, client, realId, user, name, description);
        return b;       
    }
}
