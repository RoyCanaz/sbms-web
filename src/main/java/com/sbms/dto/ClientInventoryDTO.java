/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.ClientInventory;
import com.sbms.domain.User;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class ClientInventoryDTO implements Serializable{
    private Long id;
    private Long realId;
    private String model;
    private int quantity;
    private String needMaintenence;
    private Category categories; 
    private Client client;
    private String tonerType;
    private String description;
    private Long clientId;
    private Long category;
    private User user;
    private Long createdBy;
    
    public ClientInventoryDTO() {
    }

    public ClientInventoryDTO(Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }
    

    public ClientInventoryDTO(String model, int quantity, String needMaintenence, String tonerType, String description, Long category, Client client) {
        this.model = model;
        this.quantity = quantity;
        this.needMaintenence = needMaintenence;
        this.tonerType = tonerType;
        this.description = description;
        this.category = category;
        this.client = client;
    }
    public ClientInventoryDTO(Long realId, String model, int quantity, String needMaintenence, String tonerType, String description, Long category, Long clientId, Long createdBy) {
        this.realId = realId;
        this.model = model;
        this.quantity = quantity;
        this.needMaintenence = needMaintenence;
        this.tonerType = tonerType;
        this.description = description;
        this.category = category;
        this.clientId = clientId;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
    
    public ClientInventoryDTO(String model, int quantity, String needMaintenence) {
        this.model = model;
        this.quantity = quantity;
        this.needMaintenence = needMaintenence;
    }
  
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNeedMaintenence() {
        return needMaintenence;
    }

    public void setNeedMaintenence(String needMaintenence) {
        this.needMaintenence = needMaintenence;
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


    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public String getTonerType() {
        return tonerType;
    }

    public void setTonerType(String tonerType) {
        this.tonerType = tonerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
    }
 
    public ClientInventory getInstance(ClientInventoryDTO clientInventoryDTO){
        ClientInventory clientInventory = new ClientInventory(realId, model, quantity, needMaintenence, tonerType, description, categories, client, clientId, user);
        return clientInventory;
    }
   
    
}
