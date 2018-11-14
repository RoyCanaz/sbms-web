/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.EmailType;
import com.sbms.repository.EmailAccountRepo;
import com.sbms.service.EmailAccountService;
import com.sbms.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class EmailAccountServiceImpl implements EmailAccountService{
    @Resource
    private UserService userService;
    @Resource
    private EmailAccountRepo emailAccountRepo;
    

    @Override
    public List<EmailAccount> getAll() {
        return emailAccountRepo.findAll();
    }

    @Override
    public EmailAccount get(Long id) {
        return emailAccountRepo.getOne(id);
    }

    @Override
    public void delete(EmailAccount t) {
              if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state [Id Not Found]");
        }
        t.setActive(Boolean.FALSE);
              t.setDeleted(Boolean.TRUE);
        emailAccountRepo.save(t);
    }

    @Override
    public List<EmailAccount> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailAccount save(EmailAccount t) {
        if (t.getId()==null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return emailAccountRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return emailAccountRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(EmailAccount current, EmailAccount old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmailAccount> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmailAccount> getCc(Company company) {
        List<EmailAccount> emailAccounts = new ArrayList<>();
        for(EmailAccount ea : emailAccountRepo.findByActiveAndCompany(true, company)){
            if(ea.getActive().equals(Boolean.TRUE)){
                for(EmailType et : ea.getEmailTypes()){
                    if(et.getName().equals("Cc")){
                        emailAccounts.add(ea);
                    }
                }
            }
        }
        return emailAccounts;
    }

    @Override
    public List<EmailAccount> getBcc(Company company) {
        List<EmailAccount> emailAccounts = new ArrayList<>();
        for(EmailAccount ea : emailAccountRepo.findByActiveAndCompany(true, company)){
            if(ea.getActive().equals(Boolean.TRUE)){
                for(EmailType et : ea.getEmailTypes()){
                    if(et.getName().equals("Bcc")){
                        emailAccounts.add(ea);
                    }
                }
            }
        }
        return emailAccounts;
    }

    @Override
    public List<EmailAccount> findByCompany(Company company) {
       // return emailAccountRepo.findByActiveAndCompany(true, company);
        return emailAccountRepo.findByDeletedAndCompany(Boolean.FALSE, company);
    }
  

}
