/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.RequiredDocuments;
import java.util.List;

/**
 *
 * @author user
 */
public class ClientDataMapper {
    ClientDTO client;
    ProcurementDocsDTO document;
    List<ContactDTO> contactList;
    List<BranchDTO> branchList;
    List<ClientInventoryDTO> clientInventoryList;
    List<VisitPlanDTO> visitPlanList;
    List<NotesDTO> noteList;

    public ClientDataMapper() {
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public ProcurementDocsDTO getDocument() {
        return document;
    }

    public void setDocument(ProcurementDocsDTO document) {
        this.document = document;
    }

    public List<ContactDTO> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactDTO> contactList) {
        this.contactList = contactList;
    }

    public List<BranchDTO> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<BranchDTO> branchList) {
        this.branchList = branchList;
    }

    public List<ClientInventoryDTO> getClientInventoryList() {
        return clientInventoryList;
    }

    public void setClientInventoryList(List<ClientInventoryDTO> clientInventoryList) {
        this.clientInventoryList = clientInventoryList;
    }

    public List<VisitPlanDTO> getVisitPlanList() {
        return visitPlanList;
    }

    public void setVisitPlanList(List<VisitPlanDTO> visitPlanList) {
        this.visitPlanList = visitPlanList;
    }

    public List<NotesDTO> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NotesDTO> noteList) {
        this.noteList = noteList;
    }
    
    
    
    
    
}
