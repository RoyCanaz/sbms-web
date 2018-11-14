/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.User;
import com.sbms.service.CompanyEmailService;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class PasswordResetEmailServiceImpl implements PasswordResetEmailService{

        @Autowired
	private JavaMailSender mailSender;
        @Resource
        private CompanyEmailService companyEmailService;
         @Async
        @Override
	public void sendEmail(String appUrl, User user, Company company) {
            
            try {
                 CompanyEmail emails = null;
                 if(company!=null){
                       emails = companyEmailService.getByActiveAndCompany(Boolean.TRUE, company);
                 }
                 else{
                     emails =  companyEmailService.getByActiveAndGlobalEmail(Boolean.TRUE, Boolean.TRUE);
                 }
                 
                String username = emails.getEmail();
                String password = emails.getPassword();
                Authenticator authenticator = new EmailAuth(username, password);                
                Session session = Session.getInstance(EmailProp.getProperties(emails), authenticator);          
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emails.getEmail(), emails.getAccName()!=null?emails.getAccName():"")); 
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserName()));               
                message.setSubject("Password Reset Request");
                String port = "";
                if(appUrl.contains("localhost")){
                    port = ":8080";
                }
                message.setText("To reset your password, click the link below:\n" + appUrl + port
                        + "/vimbika/password/reset?token=" + user.getResetToken());                
                Transport.send(message);
                
            } catch (AddressException ex) {
                Logger.getLogger(PasswordResetEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(PasswordResetEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(PasswordResetEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
    
}
