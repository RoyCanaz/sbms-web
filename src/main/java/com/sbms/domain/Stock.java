/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable{
         private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
   
        private String serialNumber;
        
        @Temporal(TemporalType.DATE)
        private Date datePurchased;
        
        private Boolean available = Boolean.TRUE;
        
        @ManyToOne
        @JoinColumn(name = "product_id")    
        private Product product;
        
        @ManyToOne
        @JoinColumn(name = "purchasedBy", nullable = true)    
        private Client purchasedBy;
        
        
        @Transient
        private Long categoryId;
  
        
        @ManyToOne
        @JoinColumn(name = "category_id")    
        private Category category;
        
       
        @ManyToMany(mappedBy = "invoiceStock")
        private List<Invoice> invoices = new ArrayList<>();
        
 
    
    
    

    public Stock() {
    }
        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(Client purchasedBy) {
        this.purchasedBy = purchasedBy;
    }


    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
 
}
