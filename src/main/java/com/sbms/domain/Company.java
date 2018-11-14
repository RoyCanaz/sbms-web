/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "company")
public class Company extends BaseEntity{
     
    
     private static final long serialVersionUID = 1L;
     private String companyName;
     private String description;
     private String email;
     private String website;
     private String mobilePhone;
     private String officePhone;
     private String address;
     
      @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
      @JoinTable(name = "company_module", joinColumns = {
        @JoinColumn(name = "company_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "module_id", nullable = false)})
    private Set<Modules> modules = new HashSet<>();
      
    @OneToMany(mappedBy = "company")
    private Set<User> users = new HashSet<>();
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<BankingDetail> bankingDetails = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Supplier> suppliers = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<EmailAccount> emailAccounts = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Currency> currencies = new HashSet<>();
     @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Qoute> qoutes = new HashSet<>();
    @OneToMany(mappedBy = "company")
    private Set<CompanyEmail> companyEmails = new HashSet<>();
    
    
    public Company() {
    }
      
    

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Modules> getModules() {
        return modules;
    }

    public void setModules(Set<Modules> modules) {
        this.modules = modules;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<BankingDetail> getBankingDetails() {
        return bankingDetails;
    }

    public void setBankingDetails(Set<BankingDetail> bankingDetails) {
        this.bankingDetails = bankingDetails;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }

    public void setEmailAccounts(Set<EmailAccount> emailAccounts) {
        this.emailAccounts = emailAccounts;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public Set<Qoute> getQoutes() {
        return qoutes;
    }

    public void setQoutes(Set<Qoute> qoutes) {
        this.qoutes = qoutes;
    }

    public Set<CompanyEmail> getCompanyEmails() {
        return companyEmails;
    }

    public void setCompanyEmails(Set<CompanyEmail> companyEmails) {
        this.companyEmails = companyEmails;
    }
    
    
    
    
    
}
