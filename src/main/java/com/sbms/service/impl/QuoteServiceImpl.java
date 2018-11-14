/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.User;
import com.sbms.repository.QouteRepository;
import com.sbms.service.QouteService;
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
public class QuoteServiceImpl implements QouteService{

     @Resource
     private UserService userService;
     @Resource
     private QouteRepository qouteRepository;
    @Override
    public List<Qoute> getAll() {
        return qouteRepository.findAll();
    }

    @Override
    public Qoute get(Long id) {
        return qouteRepository.getOne(id);
    }

    @Override
    public void delete(Qoute t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Qoute> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Qoute save(Qoute t) {
           if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return qouteRepository.save(t);
            }          
            
            t.setDateCreated(new Date());
            return qouteRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return qouteRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Qoute current, Qoute old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Qoute> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Qoute> getByClientId(Long id) {
        return qouteRepository.findByClientId(id);
    }

    @Override
    public List<Qoute> findByCompany(Company company) {
        return qouteRepository.findByCompany(company);
    }

    @Override
    public List<Qoute> findByCompanyAndUser(Company company, User user) {
        return qouteRepository.findByCompanyAndCreatedBy(company, user);
    }
    
}
