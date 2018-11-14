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
@Table(name = "email_type")
public class EmailType extends BaseName{
     private static final long serialVersionUID = 1L;
    @Transient
    private String printName;
    
    @ManyToMany(mappedBy = "emailTypes")
    private Set<EmailAccount> emailAccounts = new HashSet<>();

    public String getPrintName() {
        return StringUtils.toCamelCase3(super.getName());
    }

    public Set<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }

    public void setEmailAccounts(Set<EmailAccount> emailAccounts) {
        this.emailAccounts = emailAccounts;
    }
    
    
    
}
