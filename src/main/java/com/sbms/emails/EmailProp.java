/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.emails;

/**
 *
 * @author kanaz
 */

import com.sbms.domain.CompanyEmail;
import java.util.Properties;
public class EmailProp {
    public static Properties getProperties(CompanyEmail emails){
         Properties mailProps = new Properties();
                mailProps.put("mail.smtp.starttls.enable", "true");
                mailProps.put("mail.smtp.auth", "true");
                mailProps.put("mail.transport.protocol", "smtp");
                mailProps.put("mail.smtp.host", emails.getHost()); // smtp.gmail.com?
                mailProps.put("mail.smtp.port", emails.getPort());
                mailProps.put("mail.smtp.debug", "true");
                return mailProps;
    }
}
