/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author user
 */
@Entity
@Table(name = "visit_plans")
public class VisitPlan extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String status;
    private String visitResult;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Temporal(TemporalType.DATE)
    private Date dateOfVisit;
    
    @Temporal(TemporalType.DATE)
    private Date dateCalledVisited;

    @Transient
    private Long clientID;
    @Transient
    private String dateVisit;
    
    @Transient
    private Long daysRemaining;

    public VisitPlan() {
    }

    public VisitPlan(String status, String visitResult, Client client, Date dateOfVisit, Long clientId, Long id, User createdBy) {
        super(id, createdBy);
        this.status = status;
        this.visitResult = visitResult;
        this.client = client;
        this.dateOfVisit = dateOfVisit;
        this.clientID = clientID;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public Date getDateCalledVisited() {
        return dateCalledVisited;
    }

    public void setDateCalledVisited(Date dateCalledVisited) {
        this.dateCalledVisited = dateCalledVisited;
    }

    public Long getDaysRemaining() {
       if(!dateOfVisit.before(new Date())){
                long diffInMillies = Math.abs(dateOfVisit.getTime() - new Date().getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                return diff;
       }
       return 0L;
       
    }

  

    
}
