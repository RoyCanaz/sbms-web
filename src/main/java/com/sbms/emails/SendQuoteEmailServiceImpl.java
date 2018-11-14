/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.Qoute;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.QouteService;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionListener;
import javax.mail.internet.AddressException;
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
public class SendQuoteEmailServiceImpl implements SendQuoteEmailService{
   // @Autowired
  //  public JavaMailSender emailSender;
    @Resource
    private QouteService qouteService;
    
    @Resource
    private CompanyEmailService companyEmailService;
    
 
  //  @Async
    @Override
    public void sendQuoteWithAttachment(String to, String subject, String text, String[] cc, String[] bcc, String pathToAttachment, Qoute qoute, Company company) {
           
        try {
            
            
            
            CompanyEmail emails = companyEmailService.getByActiveAndCompany(Boolean.TRUE, company);
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
            message.setText(text);
            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source = new FileDataSource(new File(pathToAttachment));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(qoute.getQuoteUuid()+".pdf");
          //  messageBodyPart.setText(text);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            
            
            
           
            
          /*  
            //JavaMailSender emailSender = EmailCredentials.setCredentials(emails);        
            //String logoPath = "src/main/webapp/resources/img/nice_logo.png";
            
           // MimeMessage message = emailSender.createMimeMessage();
          
            MimeMessageHelper helper = new MimeMessageHelper(message, true);      
            helper.setTo(to);
        /*    System.err.println("====================================");
            System.err.println(bcc[0]);
            System.err.println("====================================");
             System.err.println("====================================");
            System.err.println(cc[0]);
            System.err.println("====================================");
            
          if(bcc.length!=0){
                helper.setBcc(bcc);
                
            }
            if(cc.length!=0){
               helper.setCc(cc); 
            }
           // if(cc!=null)
            helper.setSubject(subject);
            helper.setText(text);
            // helper.addInline("Total IT Solution", new FileSystemResource(logoPath));
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            
            helper.addAttachment(qoute.getQuoteUuid()+".pdf", file);  
            
            emailSender.send(message);
           
            
            */
            
            
            int count = 1+qoute.getCountNumberOfSent();
            qoute.setCountNumberOfSent(count);
            qoute.setLastSendMailStatus(1);
            qoute.setLastDateOfSent(new Date());
            qouteService.save(qoute);
        } catch (SendFailedException ex) {
            System.out.print(ex.getMessage());
        } catch (AddressException ex) {
            Logger.getLogger(SendQuoteEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendQuoteEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendQuoteEmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
            
    
    }
               
}
