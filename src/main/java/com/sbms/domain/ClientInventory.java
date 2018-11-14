/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "client_inventory")
public class ClientInventory extends BaseEntity {
       
    private String model;
    private int quantity;
    private String needMaintenence;
    private String tonerType;
    private String description;
  /*  @ManyToOne
    @JoinColumn(name = "brand_id")    
    private Brand brand; */
    @ManyToOne
    @JoinColumn(name = "category_id")    
    private Category category; 
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Transient
    private Long ci;
   
    public ClientInventory() {
    }
    public ClientInventory(Long id, String model, int quantity, String needMaintenence, String tonerType, String description, Category category, Client client, Long ci, User createdBy) {
        super(id, createdBy);
        this.model = model;
        this.quantity = quantity;
        this.needMaintenence = needMaintenence;
        this.category = category;
        this.client = client;
        this.tonerType = tonerType;
        this.description = description;
        this.ci = ci;
    }

    public Long getCi() {
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

   


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getNeedMaintenence() {
        return needMaintenence;
    }

    public void setNeedMaintenence(String needMaintenence) {
        this.needMaintenence = needMaintenence;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
       
}
