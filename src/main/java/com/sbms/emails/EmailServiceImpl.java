/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.User;
import com.sbms.emails.service.EmailService;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.UserService;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author kanaz
 */
@Component
public class EmailServiceImpl implements EmailService{
    
     @Resource
        private CompanyEmailService companyEmailService;
     @Resource
     private UserService userService;

    @Async
    @Override
    public void sendUserDetails(String url, User user, Company company) {
        
        String port = "";
                if(url.contains("localhost")){
                    port = ":8080";
                }
        CompanyEmail emails = companyEmailService.getByActiveAndCompany(Boolean.TRUE, company);
        if(emails!=null){
            try {
                String username = emails.getEmail();
                String password = emails.getPassword();
                Authenticator authenticator = new EmailAuth(username, password);
                Session session = Session.getInstance(EmailProp.getProperties(emails), authenticator);               
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                 message.setFrom(new InternetAddress(emails.getEmail(), emails.getAccName()!=null?emails.getAccName():"")); 
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserName()));   
                message.setSubject(company.getCompanyName()+" : "+"Account Created Successfully");
                message.setText("You can now login using your email address.\n"
                        + "Use this link to login  :  " +url+port+"/vimbika/");
                Transport.send(message);
                
                
            } catch (MessagingException ex) {
                Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }

    @Override
    public void sendUserDetails(String url, String roleType, User user) {
             String port = "";
                if(url.contains("localhost")){
                    port = ":8080";
                }
        CompanyEmail emails = companyEmailService.getByActiveAndGlobalEmail(Boolean.TRUE, Boolean.TRUE);
        if(emails!=null){
            try {
                String username = emails.getEmail();
                String password = emails.getPassword();
                Authenticator authenticator = new EmailAuth(username, password);
                Session session = Session.getInstance(EmailProp.getProperties(emails), authenticator);               
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                 message.setFrom(new InternetAddress(emails.getEmail(), emails.getAccName()!=null?emails.getAccName():"")); 
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserName()));   
                message.setSubject(roleType+" : "+"Account Created Successfully");
                message.setText("You can now login using your email address.\n"
                        + "Use this link to login  :  " +url+port+"/vimbika/");
                Transport.send(message);          
            } catch (MessagingException ex) {
                Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }    catch (UnsupportedEncodingException ex) {
                     Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
    }
    
    
    
    
}
