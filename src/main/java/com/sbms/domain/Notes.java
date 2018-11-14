/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "notes")
public class Notes extends BaseEntity{
     private static final long serialVersionUID = 1L;
     private String note;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
   
    @Transient
    private Long clientId;

    public Notes() {
    }

    public Notes(String note, Client client, Long clientId, Long id, User createdBy) {
        super(id, createdBy);
        this.note = note;
        this.client = client;
        this.clientId = clientId;
    }
    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    
        
}
