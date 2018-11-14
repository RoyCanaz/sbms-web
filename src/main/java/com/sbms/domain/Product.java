/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

/**
 *
 * @author user
 */
@Entity
@Table(name = "product")
@Proxy(lazy = false)
public class Product extends BaseEntity{
      
    private String model;
    private String brand;
    private String productCode;
    private String warranty;
    private String monthYear;
    @Lob
    @Column( length = 3000 )
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
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "specification_id")
    private Specification specification;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "product_addon", joinColumns = {
        @JoinColumn(name = "product_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "addon_id", nullable = false)})
    private Set<Addon> addons = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "product_supplier", joinColumns = {
        @JoinColumn(name = "product_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "supplier_id", nullable = false)})
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "product")
    private Set<QuoteItem> quoteItems = new HashSet<>();
    
    @OneToMany(mappedBy = "product")
    private List<Stock> stock = new ArrayList<>();
    
    @ManyToOne
    private Company company;
    
    /**
     * Product Specifications
     */
    @Transient
    private Long categoryId;
    @Transient
    private Long companyId;
    @Transient
    private String display;
    @Transient
    private String processor;
    @Transient
    private String memory;
    @Transient
    private String hardDrive;
    @Transient  
    private String os;
    @Transient
    private String compatibility;
    @Transient
    private String monitorSize;
    @Transient
    private String resolution;
    @Transient
    private String videoInput;
    @Transient
    private String catridge;
    @Transient
    private String color;
    @Transient
    private String dutyCycle;
    @Transient
    private String duplex;
    @Transient
    private String scanner;
    @Transient
    private String ethernet;
    @Transient
    private String wireless;
    @Transient
    private String fax;

    public Product() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Set<Addon> getAddons() {
        return addons;
    }

    public void setAddons(Set<Addon> addons) {
        this.addons = addons;
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

    public Set<QuoteItem> getQuoteItems() {
        return quoteItems;
    }

    public void setQuoteItems(Set<QuoteItem> quoteItems) {
        this.quoteItems = quoteItems;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    
    
    public List<Stock> getAvailable(){
        List<Stock> available = new ArrayList<>();
        for(Stock s : stock){
            if(s.getAvailable().equals(Boolean.TRUE)){
                available.add(s);
            }
        }
        return available;
    }
    public int stockAvailable(){
        return getAvailable().size();
    }
   
    
    public Specification addSpecification(){
        Specification spec = new Specification(display, processor, memory, hardDrive, os, compatibility, monitorSize, resolution, videoInput, catridge, color, dutyCycle, duplex, scanner, ethernet, wireless, fax);
        return spec;
    }
    
}
