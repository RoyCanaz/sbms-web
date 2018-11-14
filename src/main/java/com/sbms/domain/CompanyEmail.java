/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author user
 */
@Entity
@Table(name = "company_email")
public class CompanyEmail extends BaseEntity{
      private static final long serialVersionUID = 1L;
      private String host;
      private String port;
      private String accName;
      private String email;
      private String password;
      @Transient
      private String confirmPassword;
      @ManyToOne
      private Company company;
      @ColumnDefault("0")
      private Boolean globalEmail = Boolean.FALSE;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

      
      
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getGlobalEmail() {
        return globalEmail;
    }

    public void setGlobalEmail(Boolean globalEmail) {
        this.globalEmail = globalEmail;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }       
}
