/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "qoute")
public class Qoute extends BaseEntity{
    private static final long serialVersionUID = 1L;
  
    
    private String quoteUuid;
    private Double total;
    
    private int numOfItems;
    
    @Column(length = 500)
    private String noteSt;
    @Column(length = 500)
    private String noteNd;
    
    
    private int countNumberOfSent = 0;
    private Double vat;
    private Double totalIncVat;
    
    private int lastSendMailStatus = 0;
    
    @Temporal(TemporalType.DATE)
    private Date lastDateOfSent;
    
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
    
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    
    @ManyToOne
    @JoinColumn(name = "banking_detail_id")
    private BankingDetail bankingDetail;

   
    @OneToMany(mappedBy = "qoute")
    private List<QuoteItem> quoteItems = new ArrayList<>();
    
    @OneToMany(mappedBy = "client")
    private List<Invoice> invoice = new ArrayList<>();
    
    @ManyToOne
    Company company;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    public Qoute() {
    }

    public String getQuoteUuid() {
        return quoteUuid;
    }

    public void setQuoteUuid(String quoteUuid) {
        this.quoteUuid = quoteUuid;
    }
    
    public List<QuoteItem> getQuoteItems() {
        return quoteItems;
    }

    public void setQuoteItems(List<QuoteItem> quoteItems) {
        this.quoteItems = quoteItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BankingDetail getBankingDetail() {
        return bankingDetail;
    }

    public String getNoteSt() {
        return noteSt;
    }

    public void setNoteSt(String noteSt) {
        this.noteSt = noteSt;
    }

    public String getNoteNd() {
        return noteNd;
    }

    public void setNoteNd(String noteNd) {
        this.noteNd = noteNd;
    }


    public void setBankingDetail(BankingDetail bankingDetail) {
        this.bankingDetail = bankingDetail;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public Date getLastDateOfSent() {
        return lastDateOfSent;
    }

    public void setLastDateOfSent(Date lastDateOfSent) {
        this.lastDateOfSent = lastDateOfSent;
    }

    public int getCountNumberOfSent() {
        return countNumberOfSent;
    }

    public void setCountNumberOfSent(int countNumberOfSent) {
        this.countNumberOfSent = countNumberOfSent;
    }

    public List<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getLastSendMailStatus() {
        return lastSendMailStatus;
    }

    public void setLastSendMailStatus(int lastSendMailStatus) {
        this.lastSendMailStatus = lastSendMailStatus;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }  
    
}
