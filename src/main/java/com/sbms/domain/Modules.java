/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.sbms.utilities.StringUtils;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */

@Entity
@Table(name = "modules")
public class Modules extends BaseName{
    private static final long serialVersionUID = 1L;
    
    @Transient
    private String printName;
    @ManyToMany(mappedBy = "modules")
    private Set<Company> companies  = new HashSet<>();

    public Modules() {
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
       
    
    public String getPrintName(){
        return StringUtils.toCamelCase3(super.getName());
    }
    
}
