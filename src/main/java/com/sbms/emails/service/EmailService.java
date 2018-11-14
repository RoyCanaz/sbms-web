/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails.service;

import com.sbms.domain.Company;
import com.sbms.domain.User;

/**
 *
 * @author kanaz
 */
public interface EmailService {
    public void sendUserDetails(String url, User user, Company  company);
    public void sendUserDetails(String url, String roleType, User user);
        
}
