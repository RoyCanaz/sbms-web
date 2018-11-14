/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Client;
import com.sbms.domain.Notes;
import com.sbms.domain.User;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class NotesDTO implements Serializable {
    
    private Long id;
    private Long realId;    
    private String note;
    private Client client;
    private Long clientId;
    private User user;
    private Long createdBy;

    public NotesDTO() {
    }

    public NotesDTO(Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }
    

    public NotesDTO(Long realId, String note, Long clientId, Long createdBy) {
        this.realId = realId;
        this.note = note;
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
    
    public Notes getInstance(NotesDTO notesDTO){
        Notes notes = new Notes(note, client, clientId, realId, user);
        return notes;
    }
}
