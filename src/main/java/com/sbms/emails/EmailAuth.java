/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

/**
 *
 * @author user
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class EmailAuth extends Authenticator{
    
    String username;
    String password;

    public EmailAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
   
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
 
    
    
    
}
