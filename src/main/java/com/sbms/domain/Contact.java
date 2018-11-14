/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbms.domain.util.Gender;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "contact")
public class Contact extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
   
    private String gender;
    private String jobPosition;
    private String department;
    private String officePhone;
    private String mobilePhone;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
    
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Qoute> qoute;
    
    @Transient
    private Long ci;

    public Contact() {
    }
    
    public Contact(Long id, String firstName, String lastName, String gender, String jobPosition, String department, String officePhone, String mobilePhone, String email, Client client, Long ci, User createdBy) {
         super(id, createdBy);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.jobPosition = jobPosition;
        this.department = department;
        this.officePhone = officePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.client = client;
        this.ci = ci;
    }
   
    public Long getCi() {
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Qoute> getQoute() {
        return qoute;
    }

    public void setQoute(List<Qoute> qoute) {
        this.qoute = qoute;
    }

   
    
    
}
