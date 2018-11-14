/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity{
      private static final long serialVersionUID = 1L;
      
      
      private String invoiceUuid;
      private int countNumberOfSent;    
      
      @Temporal(TemporalType.DATE)
      private Date lastDateOfSent;
      
       @ManyToOne
       @JoinColumn(name = "quote_id")
       private Qoute qoute;
       @ManyToOne
       @JoinColumn(name = "client_id")
       private Client client;
       
       @Transient
       private Long quoteId;
       
        @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
        @JoinTable(name = "invoice_stock", joinColumns = {
        @JoinColumn(name = "invoice_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "stock_id", nullable = false)})
        private List<Stock> invoiceStock = new ArrayList<>();

        public Invoice() {
        }
       

            public String getInvoiceUuid() {
                return invoiceUuid;
            }

            public void setInvoiceUuid(String invoiceUuid) {
                this.invoiceUuid = invoiceUuid;
            }

            public int getCountNumberOfSent() {
                return countNumberOfSent;
            }

            public void setCountNumberOfSent(int countNumberOfSent) {
                this.countNumberOfSent = countNumberOfSent;
            }

            public Date getLastDateOfSent() {
                return lastDateOfSent;
            }

            public void setLastDateOfSent(Date lastDateOfSent) {
                this.lastDateOfSent = lastDateOfSent;
            }

            public Qoute getQoute() {
                return qoute;
            }

            public void setQoute(Qoute qoute) {
                this.qoute = qoute;
            }

            public Client getClient() {
                return client;
            }

            public void setClient(Client client) {
                this.client = client;
            }

            public List<Stock> getInvoiceStock() {
                return invoiceStock;
            }

            public void setInvoiceStock(List<Stock> invoiceStock) {
                this.invoiceStock = invoiceStock;
            }








            public Long getQuoteId() {
                return quoteId;
            }

            public void setQuoteId(Long quoteId) {
                this.quoteId = quoteId;
            }

    
   
}
