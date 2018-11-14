/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.CompanyEmail;
import java.util.Properties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author user
 */
public class EmailCredentials {
     public static JavaMailSender setCredentials(CompanyEmail companyEmail){
                   JavaMailSenderImpl sender = new JavaMailSenderImpl();
                   sender.setProtocol("smtp");
                   sender.setHost(companyEmail.getHost());
                   sender.setPort(Integer.valueOf(companyEmail.getPort()));
                   sender.setUsername(companyEmail.getEmail());
                   sender.setPassword(companyEmail.getPassword());
                   Properties mailProps = new Properties();
                   mailProps.put("mail.smtps.auth", "true");
                   mailProps.put("mail.smtp.starttls.required", "true");
                   mailProps.put("mail.smtp.starttls.enable", "true");
                   mailProps.put("mail.smtp.debug", "true");
                   sender.setJavaMailProperties(mailProps);
                   return sender;
                }
}
