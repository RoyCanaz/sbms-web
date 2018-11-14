/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import java.util.List;

/**
 *
 * @author user
 */
public class ClientRequestMapper {
    
    List<ClientDTO>  clientList;
    List<ContactDTO> contactList;
    List<BranchDTO> branchList;
    List<ClientInventoryDTO> inventoryList;
    List<ProcurementDocsDTO> procurementList;

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
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

    public List<ClientInventoryDTO> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<ClientInventoryDTO> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<ProcurementDocsDTO> getProcurementList() {
        return procurementList;
    }

    public void setProcurementList(List<ProcurementDocsDTO> procurementList) {
        this.procurementList = procurementList;
    }
    
    
    
    
    
    
}
