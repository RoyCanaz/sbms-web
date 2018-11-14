/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author user
 */
@MappedSuperclass
public abstract class BaseProductEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
  
    private String uuid;
   
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
  
    @ManyToOne(fetch = FetchType.LAZY)   
    private User modifiedBy;
  
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    
    @Temporal(TemporalType.DATE)
    private Date dateModified;
  
    @Version
    private Long version;
    private Boolean active = Boolean.TRUE;
    private Boolean deleted = Boolean.FALSE;

    
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    
    
    private String model;
    private String brand;
    private String productCode;
    private String warranty;
    private String description;
    private Long quantityDelivered;
    private Double landingCost;
    private Float factor;
    private Double recommendedPrice;
    private Double sellingPrice;
    private Double retailPrice;
    private Long reOrderLevel;
    private Long reOrderQuantity;
    private String serialNumber;
    @Transient
    private Long categoryId;
    
    public BaseProductEntity(){
        
    }

    
    
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(Long quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    public Double getLandingCost() {
        return landingCost;
    }

    public void setLandingCost(Double landingCost) {
        this.landingCost = landingCost;
    }

    public Float getFactor() {
        return factor;
    }

    public void setFactor(Float factor) {
        this.factor = factor;
    }

    public Double getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Double recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(Long reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public Long getReOrderQuantity() {
        return reOrderQuantity;
    }

    public void setReOrderQuantity(Long reOrderQuantity) {
        this.reOrderQuantity = reOrderQuantity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof BaseEntity)) {
            return false;
        }
        return this.getId().equals(((BaseEntity)obj).getId());
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
      
    }

    
}
