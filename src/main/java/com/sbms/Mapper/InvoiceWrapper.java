/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.Mapper;

/**
 *
 * @author user
 */
public class InvoiceWrapper {
    private Long productId;
    private String note;

    public InvoiceWrapper() {
    }
    
    

    public InvoiceWrapper(Long productId, String note) {
        this.productId = productId;
        this.note = note;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
