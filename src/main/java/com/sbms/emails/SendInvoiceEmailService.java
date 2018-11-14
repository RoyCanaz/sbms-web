/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
/**
 *
 * @author user
 */
public interface SendInvoiceEmailService {
    void sendInvoiceWithAttachment(String to, String subject, String[] cc, String[] bcc, String text, String pathToAttachment, Company company); 
    
}
