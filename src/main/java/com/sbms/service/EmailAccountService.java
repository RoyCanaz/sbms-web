/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.EmailAccount;
import java.util.List;

/**
 *
 * @author user
 */
public interface EmailAccountService extends GenericService<EmailAccount>{
    List<EmailAccount> getCc(Company company);
   // String[] getCc();
    //String[] getBcc();
    List<EmailAccount> getBcc(Company company);
    List<EmailAccount> findByCompany(Company company);
    
}
