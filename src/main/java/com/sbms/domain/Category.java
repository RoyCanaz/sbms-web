/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbms.utilities.StringUtils;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author Roy
 */
@Entity
@Table(name = "category")
@Proxy(lazy = false)
public class Category extends BaseName{
    private static final long serialVersionUID = 1L;
    @Transient
    private String printName;
 
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<ClientInventory> clientInventorys = new HashSet<>();

    
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();
    
    @OneToMany(mappedBy = "category")
    private Set<Stock> stock = new HashSet<>();

    
      @ManyToMany(mappedBy = "categories")
    private Set<Brand> brands = new HashSet<>();
    
     @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Supplier> suppliers = new HashSet<>();
   
    @ManyToOne
    private Company company;

    public Category() {
    }

    public Category(String name, String description) {
        super(name, description);
       
    }

    public Set<ClientInventory> getClientInventorys() {
        return clientInventorys;
    }

    public void setClientInventorys(Set<ClientInventory> clientInventorys) {
        this.clientInventorys = clientInventorys;
    }

   

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    
    public Set<Brand> getBrands() {
        return brands;
    }

    /*   public Set<Brand> getBrands() {
    return brands;
    }
    public void setBrands(Set<Brand> brands) {
    this.brands = brands;
    }
     */
    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Stock> getStock() {
        return stock;
    }

    public void setStock(Set<Stock> stock) {
        this.stock = stock;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    

    public int getStockSize() {
        int available = 0;
        for(Stock s : stock){
            if(s.getAvailable().equals(Boolean.TRUE)){
                available = available + 1;
            }
        }         
        return available;
    }

    public int totalStock(){
        
        return stock.size();
    }

    public int getStockPercentage() {
        double valDou = 0;
        if(getStockSize()!=0 && totalStock()!=0){
              double avail = (double)getStockSize();
              double tota = (double)totalStock();
          
             valDou = avail/tota * 100;            
        }    
        return (int)valDou;    
    }
      
    public String getPrintName(){
        return StringUtils.toCamelCase3(super.getName());
    }
}
