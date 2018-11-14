/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "currency")
public class Currency extends BaseEntity{
       private static final long serialVersionUID = 1L;
       
       private String name;
       private Float rate;
       
       @JsonIgnore
       @ManyToOne
       private Company company;
       @JsonIgnore
       @OneToMany(mappedBy = "currency")
       private List<Qoute> qoutes = new ArrayList<>();

    public Currency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Qoute> getQoutes() {
        return qoutes;
    }

    public void setQoutes(List<Qoute> qoutes) {
        this.qoutes = qoutes;
    }
       
       
       
}
