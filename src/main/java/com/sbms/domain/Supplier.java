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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String email;
    private String mobilePhone;
    private String officePhone;
    private String website;
    
    @Transient
    private String printName;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "supplier_category", joinColumns = {
        @JoinColumn(name = "supplier_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "category_id", nullable = false)})
    private Set<Category> categories = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products  = new HashSet<>();
    @JsonIgnore
    @ManyToOne
    private Company company;
    
    public Supplier() {
    }

    public Supplier(String name, String address, String email, String mobilePhone, String officePhone, String website) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.officePhone = officePhone;
        this.website = website;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

   
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
      
    public String getPrintName(){
        return StringUtils.toCamelCase3(name);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    
}
