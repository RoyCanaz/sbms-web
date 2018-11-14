/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sbms.domain.Client;
import com.sbms.domain.User;
import com.sbms.domain.VisitPlan;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
public class VisitPlanDTO implements Serializable {
    private Long id;
    
    private Long realId;   
    private String status;
    private String visitResult;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfVisit;
    private Client client;
    private Long clientId;
    private User user;
    private Long createdBy;

    public VisitPlanDTO() {
    }

    public VisitPlanDTO(Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }
    

    public VisitPlanDTO(Long realId, String status, String visitResult, Date dateOfVisit, Long clientId, Long createdBy) {
        this.realId = realId;
        this.status = status;
        this.visitResult = visitResult;
        this.dateOfVisit = dateOfVisit;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisitResult() {
        return visitResult;
    }

    public void setVisitResult(String visitResult) {
        this.visitResult = visitResult;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
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
    
    public VisitPlan getInstance(VisitPlanDTO dTO){
        VisitPlan plan = new VisitPlan(status, visitResult, client, dateOfVisit, clientId, realId, user);
        return plan;
    }
     
}
