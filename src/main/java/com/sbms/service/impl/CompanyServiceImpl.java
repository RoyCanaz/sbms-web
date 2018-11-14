/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import com.sbms.repository.CompanyRepo;
import com.sbms.service.CompanyService;
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
public class CompanyServiceImpl implements CompanyService{
    @Resource
    private CompanyRepo companyRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Company> getAll() {
        return companyRepo.findAll();
    }

    @Override
    public Company get(Long id) {
        return companyRepo.getOne(id);
    }

    @Override
    public void delete(Company t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Company save(Company t) {
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return companyRepo.save(t);
            }          
            
            t.setDateCreated(new Date());
            return companyRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return companyRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Company current, Company old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
   /* @Override
    public Company findByUser(){
        return companyRepo.findByCreatedBy(userService.getCurrentUser());
    }
*/
    @Override
    public List<Company> getActiveCompanies(Boolean active) {
        return companyRepo.findByActive(active);
    }

    @Override
    public List<Company> getByUser() {
        return companyRepo.findByCreatedBy(userService.getCurrentUser());
    }

    @Override
    public List<Company> getByActiveAndUser() {
        return companyRepo.findByActiveAndCreatedBy(Boolean.TRUE, userService.getCurrentUser());
    }
}
