/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import java.util.Date;

/**
 *
 * @author user
 */
public class QuoteDTO {
    
    private Long id;  
    private String quoteUuid;  
    private Double total;
    private int numOfItems;
    private int countNumberOfSent; 
    private Double vat;
    private Double totalIncVat;
    private int lastSendMailStatus;
    private Long contact;
    private Long currency;
    private Long client;
    private Long bankingDetail;
    private Long company;
     private String dateCreated;

    public QuoteDTO() {
    }
    

    public QuoteDTO(Long id, String dateCreated, String quoteUuid, Double total, int numOfItems, int countNumberOfSent, Double vat, Double totalIncVat, int lastSendMailStatus, Long contact, Long currency, Long client, Long bankingDetail, Long company) {
        this.id = id;
        this.quoteUuid = quoteUuid;
        this.total = total;
        this.numOfItems = numOfItems;
        this.countNumberOfSent = countNumberOfSent;
        this.vat = vat;
        this.totalIncVat = totalIncVat;
        this.lastSendMailStatus = lastSendMailStatus;
        this.contact = contact;
        this.currency = currency;
        this.client = client;
        this.bankingDetail = bankingDetail;
        this.company = company;
        this.dateCreated = dateCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuoteUuid() {
        return quoteUuid;
    }

    public void setQuoteUuid(String quoteUuid) {
        this.quoteUuid = quoteUuid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getCountNumberOfSent() {
        return countNumberOfSent;
    }

    public void setCountNumberOfSent(int countNumberOfSent) {
        this.countNumberOfSent = countNumberOfSent;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotalIncVat() {
        return totalIncVat;
    }

    public void setTotalIncVat(Double totalIncVat) {
        this.totalIncVat = totalIncVat;
    }

    public int getLastSendMailStatus() {
        return lastSendMailStatus;
    }

    public void setLastSendMailStatus(int lastSendMailStatus) {
        this.lastSendMailStatus = lastSendMailStatus;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getBankingDetail() {
        return bankingDetail;
    }

    public void setBankingDetail(Long bankingDetail) {
        this.bankingDetail = bankingDetail;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }
    
    
}
