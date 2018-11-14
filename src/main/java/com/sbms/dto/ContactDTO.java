/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Client;
import com.sbms.domain.Contact;
import com.sbms.domain.User;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class ContactDTO implements Serializable{
    private Long id;
    private Long realId;
    private String firstName;
    private String lastName;
    private String gender;
    private String jobPosition;
    private String department;
    private String officePhone;
    private String mobilePhone;
    private String email;
    private Client client;
    private Long clientId;
    private User user;
    private Long createdBy;
    
    private String displayName;
    public ContactDTO() {
    }
    

  /*  public ContactDTO(Long id, Long realId, String firstName, String lastName, String gender, String jobPosition, String department, String officePhone, String mobilePhone, String email, Client client) {
        this.id = id;
        this.realId = realId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.jobPosition = jobPosition;
        this.department = department;
        this.officePhone = officePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.client = client;
    }
*/
    
    public ContactDTO( Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }

    public ContactDTO(Long realId, String firstName, String lastName, String gender, String jobPosition, String department, String officePhone, String mobilePhone, String email, Long clientId, Long createdBy) {
        //  this.id = id;
        this.realId = realId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.jobPosition = jobPosition;
        this.department = department;
        this.officePhone = officePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.clientId = clientId;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
  
    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
     public String getDisplayName() {
        return lastName + " " + firstName;
    }

    
    
    public Contact getInstance(ContactDTO cdto){
        Contact c = new Contact(realId, firstName, lastName, gender, jobPosition, department, officePhone, mobilePhone, email, client, clientId, user);
        return c;
    }
    
    
}
