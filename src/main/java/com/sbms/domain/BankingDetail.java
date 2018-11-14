/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class BankingDetail extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String name;
    private String bank;
    private String accountNumber;
    private String branch;
    @JsonIgnore
    @OneToMany(mappedBy = "bankingDetail", cascade = CascadeType.ALL)
    private List<Qoute> qoute;
    
    @JsonIgnore
    @ManyToOne
    private Company company;

    public BankingDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<Qoute> getQoute() {
        return qoute;
    }

    public void setQoute(List<Qoute> qoute) {
        this.qoute = qoute;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

   
    
    
    
}
