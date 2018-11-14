/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Roy
 */
@Entity
@Table(name = "client")
public class Client extends BaseEntity{
    private static final long serialVersionUID = 1L;
 
    private String clientType;
    private String name;
    private String description;
    private String website;
    private String address;
    private String email;
    private String phone;  
    private String branch;
    
    @OneToMany(mappedBy = "client")
    private Set<Branch> branches = new HashSet<>();
   
    @OneToOne(mappedBy = "client")
    private RequiredDocuments requiredDocuments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Contact> contact = new HashSet<>();
    
    @OneToMany(mappedBy = "client")
    private Set<Qoute> qoutes = new HashSet<>();
    
    @OneToMany(mappedBy = "client")
    private Set<Invoice> invoices = new HashSet<>();
    
    @OneToMany(mappedBy = "purchasedBy")
    private List<Stock> purchasedBy = new ArrayList<>();
    
    @OneToMany(mappedBy = "client")
    private Set<ClientInventory> clientInventorys = new HashSet<>();
    
    @OneToMany(mappedBy = "client")
    private Set<Notes> notez = new HashSet<>();
    
    
    @ManyToOne
    private Company company;
    
    @OneToMany(mappedBy = "client")
    private Set<VisitPlan> visitPlans = new HashSet<>();
    
    public Client(){
        
    }

    public Client(String clientType, String name, String description, String website, String address, String email, String phone, String branch) {
        this.clientType = clientType;
        this.name = name;
        this.description = description;
        this.website = website;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.branch = branch;
    }

    public Client(Long id, String clientType, String name, String description, String website, String address, String email, String phone, String branch, User createdBy, Company company) {
        super(id, createdBy);
        this.clientType = clientType;
        this.name = name;
        this.description = description;
        this.website = website;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.branch = branch;
        this.company = company;
    }
    
    
    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Contact> getContact() {
        return contact;
    }

    public void setContact(Set<Contact> contact) {
        this.contact = contact;
    }


    public RequiredDocuments getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(RequiredDocuments requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public Set<ClientInventory> getClientInventorys() {
        return clientInventorys;
    }

    public void setClientInventorys(Set<ClientInventory> clientInventorys) {
        this.clientInventorys = clientInventorys;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<Notes> getNotez() {
        return notez;
    }

    public void setNotez(Set<Notes> notez) {
        this.notez = notez;
    }

   

    public Set<VisitPlan> getVisitPlans() {
        return visitPlans;
    }

    public void setVisitPlans(Set<VisitPlan> visitPlans) {
        this.visitPlans = visitPlans;
    }

    public Set<Qoute> getQoutes() {
        return qoutes;
    }

    public void setQoutes(Set<Qoute> qoutes) {
        this.qoutes = qoutes;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Stock> getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(List<Stock> purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    

    
}
