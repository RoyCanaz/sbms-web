/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.Qoute;

/**
 *
 * @author user
 */
public interface SendQuoteEmailService {
     
    
    void sendQuoteWithAttachment(String to, String subject, String text, String[] cc, String[] bcc, String pathToAttachment, Qoute quote, Company company); 
    
}
