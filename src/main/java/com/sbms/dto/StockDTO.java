/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.Invoice;
import com.sbms.domain.Product;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
public class StockDTO implements Serializable{
   
    private Long uuid;  
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datePurchased;
    private Boolean available = Boolean.TRUE; 
    private String serialNumber;
    private Product product;  
    private Long productId;
    private Client purchasedBy;
    private Long clientId;
    private Invoice invoice;
    private Long invoiceId;
    private Category category;
    private Long categoryId;

    public StockDTO() {
    }

    public StockDTO(Long uuid, Date datePurchased, Product product, Client purchasedBy, Invoice invoice, Category category) {
        this.uuid = uuid;
        this.datePurchased = datePurchased;
        this.product = product;
        this.purchasedBy = purchasedBy;
        this.invoice = invoice;
        this.category = category;
    }

    public StockDTO(Long uuid, Date datePurchased, Long productId, Long clientId, Long invoiceId, Long categoryId) {
        this.uuid = uuid;
        this.datePurchased = datePurchased;
        this.productId = productId;
        this.clientId = clientId;
        this.invoiceId = invoiceId;
        this.categoryId = categoryId;
    }

    public StockDTO(Long id, Date datePurchased, String serialNumber, Long productId, Long categoryId) {
        this.id = id;
        this.datePurchased = datePurchased;
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    
    
   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

   
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    
    
     
}
