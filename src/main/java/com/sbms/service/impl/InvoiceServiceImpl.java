/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Invoice;
import com.sbms.repository.InvoiceRepo;
import com.sbms.service.InvoiceService;
import com.sbms.service.UserService;
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
public class InvoiceServiceImpl implements InvoiceService{
     @Resource
     private UserService userService;
     @Resource
     private InvoiceRepo invoiceRepo;

    @Override
    public List<Invoice> getAll() {
        return invoiceRepo.findAll();
    }

    @Override
    public Invoice get(Long id) {
        return invoiceRepo.getOne(id);
    }

    @Override
    public void delete(Invoice t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invoice> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Invoice save(Invoice t) {
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return invoiceRepo.save(t);
            }          
            
            t.setDateCreated(new Date());
            return invoiceRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return invoiceRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Invoice current, Invoice old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invoice> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
