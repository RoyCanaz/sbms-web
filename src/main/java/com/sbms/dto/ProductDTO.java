/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Specification;
import com.sbms.domain.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class ProductDTO {
    private Long id;
    
    private Long categoryId;
    private Long companyId;
    
    private String model;
    private String brand;
    private String productCode;
    private String warranty;
    private String monthYear;
    private String description;
    private String quantityDelivered;
    private Long availableStock;
    private Double landingCost;
    private Float factor;
    private Double recommendedPrice;
    private Double sellingPrice;
    private Double retailPrice;
    private Double unitPrice;
    private Long reOrderLevel;
    private Long reOrderQuantity;
    private String serialNumber;
    private Specification specification;
    private Set<Supplier> suppliers = new HashSet<>();
    /**
     * Product Specifications
     */
    
    private String display;
    private String processor;
    private String memory;
    private String hardDrive;
    private String os;
    private String compatibility;
    private String monitorSize;
    private String resolution;
    private String videoInput;
    private String catridge;
    private String color;
    private String dutyCycle;
    private String duplex;
    private String scanner;
    private String ethernet;
    private String wireless;
    private String fax;

    public ProductDTO(Long id, String model, String brand, String productCode, String warranty, String monthYear, String description, String quantityDelivered, Long availableStock,Double sellingPrice, Double unitPrice, Specification specification) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.productCode = productCode;
        this.warranty = warranty;
        this.monthYear = monthYear;
        this.description = description;
        this.quantityDelivered = quantityDelivered;
        this.availableStock = availableStock;
        this.sellingPrice = sellingPrice;
        this.unitPrice = unitPrice;
        this.specification = specification;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(String quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    public Long getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Long availableStock) {
        this.availableStock = availableStock;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(String monitorSize) {
        this.monitorSize = monitorSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVideoInput() {
        return videoInput;
    }

    public void setVideoInput(String videoInput) {
        this.videoInput = videoInput;
    }

    public String getCatridge() {
        return catridge;
    }

    public void setCatridge(String catridge) {
        this.catridge = catridge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDutyCycle() {
        return dutyCycle;
    }

    public void setDutyCycle(String dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    public String getDuplex() {
        return duplex;
    }

    public void setDuplex(String duplex) {
        this.duplex = duplex;
    }

    public String getScanner() {
        return scanner;
    }

    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    public String getEthernet() {
        return ethernet;
    }

    public void setEthernet(String ethernet) {
        this.ethernet = ethernet;
    }

    public String getWireless() {
        return wireless;
    }

    public void setWireless(String wireless) {
        this.wireless = wireless;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
    
    
    
}
