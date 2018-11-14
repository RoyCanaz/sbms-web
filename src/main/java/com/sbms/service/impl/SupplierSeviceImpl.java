/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.Supplier;
import com.sbms.repository.SupplierRepo;
import com.sbms.service.SupplierService;
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
public class SupplierSeviceImpl implements SupplierService{
    @Resource
    private UserService userService;
    @Resource 
    private SupplierRepo supplierRepo;

    @Override
    public List<Supplier> getAll() {
        return supplierRepo.findAll();
    }

    @Override
    public Supplier get(Long id) {
         if (id == null) {
           throw new IllegalStateException("Item to be deleted does not exist:");
       }
        return supplierRepo.getOne(id);
    }

    @Override
    public void delete(Supplier t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Supplier> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Supplier save(Supplier t) {
         if (t.getId()==null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return supplierRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return supplierRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Supplier current, Supplier old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Supplier> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    @Override
    public List<Supplier> getByCategoryId(Long id) {
        return supplierRepo.findAllByCategoryId(id);
        
    }
*/

    @Override
    public List<Supplier> findByCompany(Company company) {
        return supplierRepo.findByCompany(company);
    }
   
    
}
