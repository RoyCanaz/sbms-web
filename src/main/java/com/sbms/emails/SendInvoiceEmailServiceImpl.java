/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.service.CompanyEmailService;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class SendInvoiceEmailServiceImpl implements SendInvoiceEmailService{
    
 //   @Autowired
  //  public JavaMailSender emailSender;
    @Resource
    private CompanyEmailService companyEmailService;
   // @Async
    @Override
    public void sendInvoiceWithAttachment(String to, String subject, String[] cc,  String[] bcc, String invoiceUuid, String pathToAttachment, Company company) {
     //   String logoPath = "src/main/webapp/resources/img/nice_logo.png";
      CompanyEmail emails = companyEmailService.getByActiveAndCompany(Boolean.TRUE, company);
       // JavaMailSender emailSender = EmailCredentials.setCredentials(emails);  
         try {
             
            String username = emails.getEmail();
            String password = emails.getPassword();
            
           Authenticator authenticator = new EmailAuth(username, password);
          
            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.starttls.enable", "true");    
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.transport.protocol", "smtp");
            mailProps.put("mail.smtp.host", emails.getHost()); // smtp.gmail.com?
            mailProps.put("mail.smtp.port", emails.getPort());      
            mailProps.put("mail.smtp.debug", "true");
            Session session = Session.getInstance(mailProps, authenticator);
            
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           for(int i = 0; i<cc.length; i++){
                
                  if( cc[i]==null ){
                  }
                  else{
                    Address toCC=new InternetAddress(cc[i]);
                    message.addRecipient(Message.RecipientType.CC, toCC);
                  }
            }
            for(int i = 0; i<bcc.length; i++){ 
               
                  if(bcc[i]==null){
                      
                  }else{
               
                     Address toBcc = new InternetAddress(bcc[i]);                
                     message.addRecipient(Message.RecipientType.BCC, toBcc);
                 }                  
            }
            
            message.setFrom(new InternetAddress(emails.getEmail(), emails.getAccName()!=null?emails.getAccName():"")); 
            message.setSubject(subject);
            message.setText("Please Find Attached Invoice.");
            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source = new FileDataSource(new File(pathToAttachment));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(invoiceUuid+".pdf");
           // messageBodyPart.setText("Please Find Attached Invoice.");
            multipart.addBodyPart(messageBodyPart);
           
            message.setContent(multipart);
            Transport.send(message);          
        } catch (MessagingException ex) {
            Logger.getLogger(SendQuoteEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendInvoiceEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
