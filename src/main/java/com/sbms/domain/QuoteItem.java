/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author user
 */
@Entity
@Table(name = "qoute_items")
@Proxy(lazy = false)
public class QuoteItem implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    private Product product;
    @Column(length = 400)
    private String note;
    private Long quantity;
    
    @ManyToOne
    @JoinColumn(name = "qoute_id")    
    private Qoute qoute;
    
    public QuoteItem() {
    }
    
   
    public QuoteItem(Long id, Product product, Long quantity, String note, Qoute qoute) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.note = note;
        this.qoute = qoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   
    
/*
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Qoute> getQoutes() {
        return qoutes;
    }

    public void setQoutes(List<Qoute> qoutes) {
        this.qoutes = qoutes;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((QuoteItem)obj).getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addQuoteItem(Product product){
       
            this.id = product.getId();
            this.brand = product.getBrand();
            this.category = product.getCategory();
            this.productCode = product.getProductCode();
            this.sellingPrice = product.getSellingPrice();
            this.serialNumber = product.getSerialNumber();
            this.quantity = 1L;
            this.model = product.getModel();
            this.description = product.getDescription();
    
        }
    */    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    public Qoute getQoute() {
        return qoute; 
    }

    /* public List<Qoute> getQoutes() {
    return qoutes;
    }
    public void setQoutes(List<Qoute> qoutes) {
    this.qoutes = qoutes;
    }
     */
    public void setQoute(Qoute qoute) {
        this.qoute = qoute;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getProduct().getId() == ((QuoteItem)obj).getProduct().getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addQuoteItem(Product pro){           
            this.product = pro;          
            this.quantity = 1L;   
        }
    
    
}
