/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

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

/**
 *
 * @author user
 */
@Entity
@Table(name = "email_account")
public class EmailAccount extends BaseEntity {
     private static final long serialVersionUID = 1L;
     private String email;
     private String description;
     
     @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
     @JoinTable(name = "email_acc_type", joinColumns = {
        @JoinColumn(name = "email_account_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "email_type_id", nullable = false)})
    private Set<EmailType> emailTypes = new HashSet<>();
     
    @ManyToOne
    private Company company;

    public EmailAccount() {
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EmailType> getEmailTypes() {
        return emailTypes;
    }

    public void setEmailTypes(Set<EmailType> emailTypes) {
        this.emailTypes = emailTypes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
     
     
    
    
}
