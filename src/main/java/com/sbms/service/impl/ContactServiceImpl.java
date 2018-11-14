/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Contact;
import com.sbms.domain.Currency;
import com.sbms.repository.ContactRepository;
import com.sbms.service.ContactService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class ContactServiceImpl implements ContactService{

    
    @Resource
    private ContactRepository contactRepository;
    @Resource
    private UserService userService;
   

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact get(Long id) {
     //  return contactRepository.getOne(id);
        Optional<Contact> optional = contactRepository.findById(id);        
        return optional.get();
    }

    @Override
    public void delete(Contact t) {
           if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        contactRepository.save(t);
    }

    @Override
    public List<Contact> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact save(Contact t) {       
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return contactRepository.save(t);
            }                     
            t.setDateCreated(new Date());
            return contactRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return contactRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Contact current, Contact old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contact> findByActiveAndClientId(Long id) {
        return contactRepository.findByActiveAndClientId(Boolean.TRUE, id);
    }

    @Override
    public List<Contact> getCreatedBy(Long createdBy) {
        return contactRepository.findByActiveAndCreatedById(Boolean.TRUE, createdBy);
    }
    
}
