/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author user
 */
public interface PasswordResetEmailService {
    public void sendEmail(String appUrl, User user, Company company);
}
