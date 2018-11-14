/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.RequiredDocuments;
import com.sbms.repository.RequiredDocumentsRepository;
import com.sbms.service.RequiredDocumentsService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class RequiredDocumentsImpl implements RequiredDocumentsService{
     @Resource
    private RequiredDocumentsRepository documentsRepository;
    @Resource
    private UserService userService;
    @Override
    public List<RequiredDocuments> getAll() {
        return documentsRepository.findAll();
    }

    @Override
    public RequiredDocuments get(Long id) {
        if (id == null) {
            throw new IllegalStateException("Item to be deleted does not exist:");
        }
        Optional<RequiredDocuments> optional = documentsRepository.findById(id);
        RequiredDocuments rd = optional.get();
        return rd;
    }

    @Override
    public void delete(RequiredDocuments t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RequiredDocuments> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequiredDocuments save(RequiredDocuments t) {
        
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return documentsRepository.save(t);
            }          
            
            t.setDateCreated(new Date());
            return documentsRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return documentsRepository.save(t);
       
    }

    @Override
    public Boolean checkDuplicate(RequiredDocuments current, RequiredDocuments old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequiredDocuments findByClient(Long id) {
        return documentsRepository.findByClientId(id);
    }

    @Override
    public List<RequiredDocuments> getCreatedBy(Long createdBy) {
        return documentsRepository.findByCreatedById(createdBy);
    }
    
}
